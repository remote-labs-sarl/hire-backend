package com.remotelabs.hire.repositories.specs;

import com.remotelabs.hire.dtos.CandidateResource;
import com.remotelabs.hire.dtos.filters.CandidateFilter;
import com.remotelabs.hire.entities.Candidate;
import com.remotelabs.hire.entities.Country;
import com.remotelabs.hire.entities.Technology;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class CandidateSpecs {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<CandidateResource> getCandidates(CandidateFilter candidateFilter, int page, int size) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Candidate> criteriaQuery = criteriaBuilder.createQuery(Candidate.class);
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Candidate> candidate = criteriaQuery.from(Candidate.class);
        Join<Candidate, Country> country = candidate.join("country", JoinType.INNER);
        Join<Candidate, Technology> technology = candidate.join("technology", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        if (candidateFilter.getCountryId() != null) {
            predicates.add(criteriaBuilder.equal(country.get("id"),
                    candidateFilter.getCountryId()));
        }
        if (candidateFilter.getType() != null) {
            predicates.add(criteriaBuilder.equal(candidate.get("type"),
                    candidateFilter.getType()));
        }
        if (candidateFilter.getSalaryExpectation() != null) {
            criteriaBuilder.lessThanOrEqualTo(candidate.get("salaryExpectation"),
                    candidateFilter.getSalaryExpectation());
        }
        if (candidateFilter.getNoticePeriod() != null) {
            criteriaBuilder.equal(candidate.get("noticePeriod"),
                    candidateFilter.getNoticePeriod());
        }

        criteriaQuery
                .select(candidate)
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery<Candidate> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult((page)*size);
        query.setMaxResults(size);
        List<Candidate> results = query.getResultList();

        countQuery.select(criteriaBuilder.count(countQuery.from(Candidate.class)));
        long totalElements = entityManager.createQuery(countQuery).getSingleResult();

        Page<Candidate> candidates = new PageImpl<>(results, PageRequest.of(page, size), totalElements);

        return Page.empty();
    }
}
