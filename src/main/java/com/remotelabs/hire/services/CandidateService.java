package com.remotelabs.hire.services;

import com.remotelabs.hire.dtos.CandidateResource;
import com.remotelabs.hire.enums.CandidateType;
import com.remotelabs.hire.filters.CandidateFilter;
import com.remotelabs.hire.repositories.specs.CandidateSpecs;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateSpecs candidateSpecs;

    public Page<CandidateResource> getCandidates(CandidateFilter candidateFilter, int page, int size) {

        return candidateSpecs.getCandidates(candidateFilter, page, size);
    }
}
