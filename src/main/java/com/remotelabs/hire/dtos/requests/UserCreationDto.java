package com.remotelabs.hire.dtos.requests;

import com.remotelabs.hire.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationDto {

    private boolean enabled;
    private String username;
    private String password;
    private UserRole userRole;
}
