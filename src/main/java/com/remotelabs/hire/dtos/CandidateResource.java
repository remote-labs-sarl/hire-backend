package com.remotelabs.hire.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateResource {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private double percentageMatch;
    private String country;
}
