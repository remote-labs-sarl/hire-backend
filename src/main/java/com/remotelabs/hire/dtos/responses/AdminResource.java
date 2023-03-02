package com.remotelabs.hire.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class AdminResource {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Date createdOn;
}
