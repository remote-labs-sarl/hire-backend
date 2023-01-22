package com.remotelabs.hire.repositories.specs;

import com.remotelabs.hire.dtos.CandidateResource;
import com.remotelabs.hire.entities.Candidate;
import com.remotelabs.hire.entities.Country;
import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.filters.CandidateFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CandidateSpecs {

    @PersistenceContext
    private final EntityManager entityManager;

    public Page<CandidateResource> getCandidates(CandidateFilter candidateFilter, int page, int size) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Candidate> candidate = criteriaQuery.from(Candidate.class);
        From<Candidate, Country> country = candidate.join("country");
        From<Candidate, Technology> technology = candidate.join("technology");

        Predicate typePredicate = criteriaBuilder.equal(candidate.get("type"), candidateFilter.getType());
        Predicate salaryPredicate = criteriaBuilder.lessThanOrEqualTo(candidate.get("salaryExpectation"),
                candidateFilter.getSalaryExpectation());

        Predicate yearsOfExperiencePredicate = criteriaBuilder.equal(candidate.get("yearsOfExperience"),
                candidateFilter.getNoticePeriod());

        Predicate countryPredicate = criteriaBuilder.equal(candidate.get("country"), candidateFilter.getCountryId());
        Predicate noticePeriodPredicate = criteriaBuilder.equal(candidate.get("noticePeriod"),
                candidateFilter.getNoticePeriod());

        Predicate languagesPredicates = criteriaBuilder.equal(candidate.get("languages"),
                candidateFilter.getNoticePeriod());

        Predicate additionalSkillsPredicate = criteriaBuilder.equal(candidate.get("languages"),
                candidateFilter.getNoticePeriod());

        Predicate keywordsPredicate = criteriaBuilder.equal(candidate.get("languages"),
                candidateFilter.getNoticePeriod());

        criteriaQuery.multiselect(candidate, country, technology);

        List<Object[]> results = entityManager.createQuery(criteriaQuery).getResultList();

        for (Object[] obj : results) {
            Candidate user = (Candidate) obj[0];
            Country address = (Country) obj[1];
            Technology city = (Technology) obj[2];
        }

        return Page.empty();
    }
}
