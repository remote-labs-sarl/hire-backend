package com.remotelabs.hire.repositories.criteria;

import com.remotelabs.hire.converters.CandidateConverter;
import com.remotelabs.hire.dtos.responses.CandidateResource;
import com.remotelabs.hire.dtos.requests.CandidateSearchRequest;
import com.remotelabs.hire.entities.Candidate;
import com.remotelabs.hire.entities.Country;
import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.enums.SortCandidateBy;
import com.remotelabs.hire.enums.SortOrder;
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
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CandidateCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final CandidateConverter candidateConverter;

    public Page<CandidateResource> findCandidatesByFilter(CandidateSearchRequest candidateSearchRequest, int page, int size) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Candidate> criteriaQuery = criteriaBuilder.createQuery(Candidate.class);
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Candidate> candidateRoot = criteriaQuery.from(Candidate.class);
        Join<Candidate, Country> candidateCountryJoin = candidateRoot.join("country", JoinType.INNER);
        Join<Candidate, Technology> candidateTechnologyJoin = candidateRoot.join("mainTechnology", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        applyFilters(candidateSearchRequest, criteriaBuilder, candidateRoot,
                candidateCountryJoin, candidateTechnologyJoin, predicates);

        criteriaQuery
                .select(candidateRoot)
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Candidate> query = entityManager.createQuery(criteriaQuery);

        Sort sort = applySorting(candidateSearchRequest);

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        countQuery.select(criteriaBuilder.count(countQuery.from(Candidate.class)));
        long totalElements = entityManager.createQuery(countQuery).getSingleResult();

        Page<Candidate> candidates = new PageImpl<>(query.getResultList(), pageRequest, totalElements);
        return candidates.map(candidateConverter::convert);
    }

    private static Sort applySorting(CandidateSearchRequest candidateSearchRequest) {

        Map<SortCandidateBy, SortOrder> sortBy = candidateSearchRequest.getSortBy();
        if (sortBy.containsKey(SortCandidateBy.FIRSTNAME)) {
            if (sortBy.get(SortCandidateBy.FIRSTNAME) == SortOrder.ASC) {
                return Sort.by("firstName").ascending();
            } else if (sortBy.get(SortCandidateBy.FIRSTNAME) == SortOrder.DESC) {
                return Sort.by("firstName").descending();
            }
        }

       else if (sortBy.containsKey(SortCandidateBy.MIDDLE_NAME)) {
            if (sortBy.get(SortCandidateBy.MIDDLE_NAME) == SortOrder.ASC) {
                return Sort.by("middleName").ascending();
            } else if (sortBy.get(SortCandidateBy.MIDDLE_NAME) == SortOrder.DESC) {
                return Sort.by("middleName").descending();
            }
        }

       else if (sortBy.containsKey(SortCandidateBy.LASTNAME)) {
            if (sortBy.get(SortCandidateBy.FIRSTNAME) == SortOrder.ASC) {
                return Sort.by("firstName").ascending();
            } else if (sortBy.get(SortCandidateBy.FIRSTNAME) == SortOrder.DESC) {
                return Sort.by("firstName").descending();
            }
        }
        return Sort.by("firstName").ascending();
    }

    private static void applyFilters(CandidateSearchRequest candidateSearchRequest,
                                     CriteriaBuilder criteriaBuilder,
                                     Root<Candidate> candidate,
                                     Join<Candidate, Country> country,
                                     Join<Candidate, Technology> technology,
                                     List<Predicate> predicates) {

        if (candidateSearchRequest.getMainTechnologyId() != null) {

            predicates.add(criteriaBuilder.equal(technology.get("id"),
                    candidateSearchRequest.getMainTechnologyId()));
        }
        if (candidateSearchRequest.getType() != null) {

            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(candidate.get("type")),
                    candidateSearchRequest.getType().name().toLowerCase()));
        }
        if (candidateSearchRequest.getSalaryExpectation() != null) {

            predicates.add(criteriaBuilder.lessThanOrEqualTo(candidate.get("salaryExpectation"),
                    candidateSearchRequest.getSalaryExpectation()));
        }
        if (candidateSearchRequest.getCountryId() != null) {

            predicates.add(criteriaBuilder.equal(country.get("id"),
                    candidateSearchRequest.getCountryId()));
        }
        if (candidateSearchRequest.getYearsOfExperience() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(candidate.get("yearsOfExperience"),
                    candidateSearchRequest.getYearsOfExperience()));
        }
        if (candidateSearchRequest.getNoticePeriod() != null) {

            predicates.add(criteriaBuilder.lessThanOrEqualTo(candidate.get("noticePeriod"),
                    candidateSearchRequest.getNoticePeriod()));
        }
        if (candidateSearchRequest.getLanguages() != null && !candidateSearchRequest.getLanguages().isEmpty()) {

            predicates.add(candidate.get("languages").in(candidateSearchRequest.getLanguages()));
        }

        if (candidateSearchRequest.getKeywords() != null && !candidateSearchRequest.getKeywords().isEmpty()) {

            candidateSearchRequest.getKeywords().forEach(keyword ->
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(candidate.get("tags")),
                                    "%" + keyword.toLowerCase() + "%"),
                            criteriaBuilder.like(criteriaBuilder.lower(candidate.get("type")),
                                    "%" + keyword.toLowerCase() + "%"))));
        }
    }
}
