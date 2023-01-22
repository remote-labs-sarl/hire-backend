package com.remotelabs.hire.repositories.specs;

import com.remotelabs.hire.dtos.CandidateResource;
import com.remotelabs.hire.filters.CandidateFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CandidateSpecs {

    @PersistenceContext
    private final EntityManager entityManager;

    public Page<CandidateResource> getCandidates(CandidateFilter candidateFilter, int page, int size) {

        return Page.empty();
    }
}
