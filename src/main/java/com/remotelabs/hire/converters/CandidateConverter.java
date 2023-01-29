package com.remotelabs.hire.converters;

import com.remotelabs.hire.dtos.CandidateResource;
import com.remotelabs.hire.entities.Candidate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CandidateConverter {

    public CandidateResource convert(Candidate candidate) {

        return CandidateResource.builder()
                .id(candidate.getId())
                .lastName(candidate.getLastName())
                .middleName(candidate.getMiddleName())
                .lastName(candidate.getLastName())
                .country(candidate.getCountry().getName())
                .percentageMatch(50 + (50 - 99) * new Random().nextDouble())
                .build();
    }
}
