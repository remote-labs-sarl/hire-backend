package com.remotelabs.hire.converters;

import com.remotelabs.hire.dtos.responses.CandidateResource;
import com.remotelabs.hire.entities.Candidate;
import org.springframework.stereotype.Component;

@Component
public class CandidateConverter {

    public CandidateResource convert(Candidate candidate) {

        return CandidateResource.builder()
                .id(candidate.getId())
                .lastName(candidate.getLastName())
                .middleName(candidate.getMiddleName())
                .lastName(candidate.getLastName())
                .country(candidate.getCountry().getName())
                .build();
    }
}
