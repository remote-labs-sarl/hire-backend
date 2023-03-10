package com.remotelabs.hire.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "candidate_tech_skills", uniqueConstraints = {@UniqueConstraint(columnNames =
        {"candidate_id", "tech_skill_id"})})
public class CandidateTechSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "candidate_id")
    private Long candidateId;

    @Column(nullable = false, name = "tech_skill_id")
    private Long techSkillId;
    private boolean main;
    private int yearsOfExperience;
}
