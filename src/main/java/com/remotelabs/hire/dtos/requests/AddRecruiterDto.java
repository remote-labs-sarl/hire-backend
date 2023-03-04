package com.remotelabs.hire.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRecruiterDto {

    @NotNull(message = "Missing required field firstName")
    private String firstName;
    @NotNull(message = "Missing required field middleName")
    private String middleName;
    @NotNull(message = "Missing required field lastName")
    private String lastName;

    @NotNull(message = "Missing required field loginDetails")
    private AddUserDto loginDetails;
}
