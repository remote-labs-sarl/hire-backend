package com.remotelabs.hire.repositories;

import com.remotelabs.hire.entities.BusinessSkill;
import com.remotelabs.hire.entities.SoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftSkillRepository extends JpaRepository<SoftSkill, Long> {
}
