package com.remotelabs.hire.dtos.requests;

import com.remotelabs.hire.entities.CandidateTechSkill;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CandidateSkillsDto {

    @NotEmpty(message = "Missing required field candidateTechSkills")
    private List<CandidateTechSkill> candidateTechSkills;
    private List<Long> businessSkillsId;
    private List<Long> softSkillIds;
}
