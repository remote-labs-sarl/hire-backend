package com.remotelabs.hire.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "candidate_soft_skills", uniqueConstraints = {@UniqueConstraint(columnNames =
        {"candidate_id", "soft_skill_id"})})
public class CandidateSoftSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "candidate_id")
    private Long candidateId;

    @Column(nullable = false, name = "soft_skill_id")
    private Long softSkillId;
}
