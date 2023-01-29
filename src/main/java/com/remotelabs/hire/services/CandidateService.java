package com.remotelabs.hire.services;

import com.remotelabs.hire.dtos.CandidateResource;
import com.remotelabs.hire.dtos.filters.CandidateFilter;
import com.remotelabs.hire.repositories.criteria.CandidateCriteriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateCriteriaRepository candidateCriteriaRepository;

    @Transactional
    public Page<CandidateResource> getCandidates(CandidateFilter candidateFilter, int page, int size) {

        return candidateCriteriaRepository.findCandidatesByFilter(candidateFilter, page, size);
    }
}
