package com.remotelabs.hire.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotNull(message = "Missing required field username")
    private String username;

    @NotNull(message = "Missing required field password")
    private String password;
}
