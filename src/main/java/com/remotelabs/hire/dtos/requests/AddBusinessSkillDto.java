package com.remotelabs.hire.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBusinessSkillDto {

    @NotEmpty(message = "Missing required field name")
    private String name;
}
