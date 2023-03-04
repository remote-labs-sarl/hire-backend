package com.remotelabs.hire.repositories.criteria;

import com.remotelabs.hire.dtos.requests.SearchCandidateDto;
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

    public static final String FIRST_NAME = "firstName";
    public static final String MIDDLE_NAME = "middleName";
    public static final String LAST_NAME = "lastName";
    @PersistenceContext
    private EntityManager entityManager;

    public Page<Candidate> findCandidatesByFilter(SearchCandidateDto searchCandidateDto,
                                                  int page, int size) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Candidate> criteriaQuery = criteriaBuilder.createQuery(Candidate.class);
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Candidate> candidateRoot = criteriaQuery.from(Candidate.class);
        Join<Candidate, Country> candidateCountryJoin = candidateRoot.join("country", JoinType.INNER);
        Join<Candidate, Technology> candidateTechnologyJoin = candidateRoot.join("mainTechnology", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        applyFilters(searchCandidateDto, criteriaBuilder, candidateRoot,
                candidateCountryJoin, candidateTechnologyJoin, predicates);

        criteriaQuery
                .select(candidateRoot)
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Candidate> query = entityManager.createQuery(criteriaQuery);

        Sort sort = applySorting(searchCandidateDto);

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        countQuery.select(criteriaBuilder.count(countQuery.from(Candidate.class)));
        long totalElements = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(query.getResultList(), pageRequest, totalElements);
    }

    private static void applyFilters(SearchCandidateDto searchCandidateDto,
                                     CriteriaBuilder criteriaBuilder,
                                     Root<Candidate> candidate,
                                     Join<Candidate, Country> country,
                                     Join<Candidate, Technology> technology,
                                     List<Predicate> predicates) {

        if (searchCandidateDto.getMainTechnologyId() != null) {

            predicates.add(criteriaBuilder.equal(technology.get("id"),
                    searchCandidateDto.getMainTechnologyId()));
        }
        if (searchCandidateDto.getType() != null) {

            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(candidate.get("type")),
                    searchCandidateDto.getType().name().toLowerCase()));
        }
        if (searchCandidateDto.getSalaryExpectation() != null) {

            predicates.add(criteriaBuilder.lessThanOrEqualTo(candidate.get("salaryExpectation"),
                    searchCandidateDto.getSalaryExpectation()));
        }
        if (searchCandidateDto.getCountryId() != null) {

            predicates.add(criteriaBuilder.equal(country.get("id"),
                    searchCandidateDto.getCountryId()));
        }
        if (searchCandidateDto.getYearsOfExperience() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(candidate.get("yearsOfExperience"),
                    searchCandidateDto.getYearsOfExperience()));
        }
        if (searchCandidateDto.getNoticePeriod() != null) {

            predicates.add(criteriaBuilder.lessThanOrEqualTo(candidate.get("noticePeriod"),
                    searchCandidateDto.getNoticePeriod()));
        }
        if (searchCandidateDto.getLanguages() != null && !searchCandidateDto.getLanguages().isEmpty()) {

            predicates.add(candidate.get("languages").in(searchCandidateDto.getLanguages()));
        }

        if (searchCandidateDto.getKeywords() != null && !searchCandidateDto.getKeywords().isEmpty()) {

            searchCandidateDto.getKeywords().forEach(keyword ->
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(candidate.get("tags")),
                                    "%" + keyword.toLowerCase() + "%"),
                            criteriaBuilder.like(criteriaBuilder.lower(candidate.get("type")),
                                    "%" + keyword.toLowerCase() + "%"))));
        }
    }

    private static Sort applySorting(SearchCandidateDto searchCandidateDto) {

        Map<SortCandidateBy, SortOrder> sortBy = searchCandidateDto.getSortBy();
        if (sortBy.containsKey(SortCandidateBy.FIRSTNAME)) {
            if (sortBy.get(SortCandidateBy.FIRSTNAME) == SortOrder.ASC) {
                return Sort.by(FIRST_NAME).ascending();
            } else if (sortBy.get(SortCandidateBy.FIRSTNAME) == SortOrder.DESC) {
                return Sort.by(FIRST_NAME).descending();
            }
        } else if (sortBy.containsKey(SortCandidateBy.MIDDLE_NAME)) {
            if (sortBy.get(SortCandidateBy.MIDDLE_NAME) == SortOrder.ASC) {
                return Sort.by(MIDDLE_NAME).ascending();
            } else if (sortBy.get(SortCandidateBy.MIDDLE_NAME) == SortOrder.DESC) {
                return Sort.by(MIDDLE_NAME).descending();
            }
        } else if (sortBy.containsKey(SortCandidateBy.LASTNAME)) {
            if (sortBy.get(SortCandidateBy.FIRSTNAME) == SortOrder.ASC) {
                return Sort.by(LAST_NAME).ascending();
            } else if (sortBy.get(SortCandidateBy.FIRSTNAME) == SortOrder.DESC) {
                return Sort.by(LAST_NAME).descending();
            }
        }
        return Sort.by(FIRST_NAME).ascending();
    }
}
