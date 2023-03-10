package com.remotelabs.hire.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CandidateResource {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private double percentageMatch;
    private String country;

    private String picUrl;
}
