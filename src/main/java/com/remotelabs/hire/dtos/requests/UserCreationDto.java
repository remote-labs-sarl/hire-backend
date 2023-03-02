package com.remotelabs.hire.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationDto {

    private String username;
    private String password;
}
