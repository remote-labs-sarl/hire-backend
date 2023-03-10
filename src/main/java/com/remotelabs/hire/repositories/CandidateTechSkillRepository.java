package com.remotelabs.hire.repositories;

import com.remotelabs.hire.entities.CandidateTechSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateTechSkillRepository extends JpaRepository<CandidateTechSkill, Long> {
}
