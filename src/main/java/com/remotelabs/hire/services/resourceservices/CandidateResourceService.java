package com.remotelabs.hire.services.resourceservices;

import com.remotelabs.hire.converters.CandidateConverter;
import com.remotelabs.hire.dtos.requests.AddCandidateDto;
import com.remotelabs.hire.dtos.requests.CandidateSearchDto;
import com.remotelabs.hire.dtos.responses.CandidateResource;
import com.remotelabs.hire.entities.Candidate;
import com.remotelabs.hire.services.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequiredArgsConstructor
public class CandidateResourceService {

    private final CandidateService candidateService;
    private final CandidateConverter candidateConverter;

    public Page<CandidateResource> getCandidates(@RequestBody CandidateSearchDto candidateSearchDto,
                                                 @RequestParam int page,
                                                 @RequestParam int size) {

        Page<Candidate> candidates = candidateService.getCandidates(candidateSearchDto, page, size);
        return candidates.map(candidateConverter::convert);
    }

    public void addCandidate(AddCandidateDto addCandidateDto) {

        candidateService.addCandidate(addCandidateDto);
    }
}
