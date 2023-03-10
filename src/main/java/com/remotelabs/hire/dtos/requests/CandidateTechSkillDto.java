package com.remotelabs.hire.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateTechSkillDto {

    private Long techSkillId;
    private int yearsOfExperience;
    private boolean addAsMainSkill;
}
