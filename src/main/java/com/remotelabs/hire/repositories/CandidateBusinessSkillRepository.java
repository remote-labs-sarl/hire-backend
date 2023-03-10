package com.remotelabs.hire.repositories;

import com.remotelabs.hire.entities.CandidateBusinessSkill;
import com.remotelabs.hire.entities.CandidateSoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateBusinessSkillRepository extends JpaRepository<CandidateBusinessSkill, Long> {
}
