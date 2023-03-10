package com.remotelabs.hire.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SoftSkillResource {

    private Long id;
    private String name;
}
