package com.remotelabs.hire.repositories;

import com.remotelabs.hire.entities.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRoleRepository extends JpaRepository<JobRole, Long> {
}
