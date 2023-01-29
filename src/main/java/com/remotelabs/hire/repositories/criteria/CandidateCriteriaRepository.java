package com.remotelabs.hire.repositories.criteria;

import com.remotelabs.hire.converters.CandidateConverter;
import com.remotelabs.hire.dtos.CandidateResource;
import com.remotelabs.hire.dtos.filters.CandidateFilter;
import com.remotelabs.hire.entities.Candidate;
import com.remotelabs.hire.entities.Country;
import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.enums.SortCandidateBy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CandidateCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final CandidateConverter candidateConverter;

    public Page<CandidateResource> findCandidatesByFilter(CandidateFilter candidateFilter, int page, int size) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Candidate> criteriaQuery = criteriaBuilder.createQuery(Candidate.class);
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Candidate> candidateRoot = criteriaQuery.from(Candidate.class);
        Join<Candidate, Country> candidateCountryJoin = candidateRoot.join("country", JoinType.INNER);
        Join<Candidate, Technology> candidateTechnologyJoin = candidateRoot.join("mainTechnology", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        applyFilters(candidateFilter, criteriaBuilder, candidateRoot,
                candidateCountryJoin, candidateTechnologyJoin, predicates);

        criteriaQuery
                .select(candidateRoot)
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Candidate> query = entityManager.createQuery(criteriaQuery);

        Sort sort = Sort.by("firstName").ascending();

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        List<Candidate> results = query.getResultList();

        countQuery.select(criteriaBuilder.count(countQuery.from(Candidate.class)));
        long totalElements = entityManager.createQuery(countQuery).getSingleResult();

        Page<Candidate> candidates = new PageImpl<>(results, pageRequest, totalElements);
        return candidates.map(candidateConverter::convert);
    }

    private static void applyFilters(CandidateFilter candidateFilter,
                                     CriteriaBuilder criteriaBuilder,
                                     Root<Candidate> candidate,
                                     Join<Candidate, Country> country,
                                     Join<Candidate, Technology> technology,
                                     List<Predicate> predicates) {

        if (candidateFilter.getMainTechnologyId() != null) {

            predicates.add(criteriaBuilder.equal(technology.get("id"),
                    candidateFilter.getMainTechnologyId()));
        }
        if (candidateFilter.getType() != null) {

            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(candidate.get("type")),
                    candidateFilter.getType().name().toLowerCase()));
        }
        if (candidateFilter.getSalaryExpectation() != null) {

            predicates.add(criteriaBuilder.lessThanOrEqualTo(candidate.get("salaryExpectation"),
                    candidateFilter.getSalaryExpectation()));
        }
        if (candidateFilter.getCountryId() != null) {

            predicates.add(criteriaBuilder.equal(country.get("id"),
                    candidateFilter.getCountryId()));
        }
        if (candidateFilter.getYearsOfExperience() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(candidate.get("yearsOfExperience"),
                    candidateFilter.getYearsOfExperience()));
        }
        if (candidateFilter.getNoticePeriod() != null) {

            predicates.add(criteriaBuilder.lessThanOrEqualTo(candidate.get("noticePeriod"),
                    candidateFilter.getNoticePeriod()));
        }
        if (candidateFilter.getLanguages() != null && !candidateFilter.getLanguages().isEmpty()) {

            predicates.add(candidate.get("languages").in(candidateFilter.getLanguages()));
        }

        if (candidateFilter.getKeywords() != null && !candidateFilter.getKeywords().isEmpty()) {

            candidateFilter.getKeywords().forEach(keyword ->
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(candidate.get("tags")),
                                    "%" + keyword.toLowerCase() + "%"),
                            criteriaBuilder.like(criteriaBuilder.lower(candidate.get("type")),
                                    "%" + keyword.toLowerCase() + "%"))));
        }
    }
}
