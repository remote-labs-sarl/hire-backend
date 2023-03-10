package com.remotelabs.hire.repositories;

import com.remotelabs.hire.entities.SalaryExpectation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryExpectationRepository extends JpaRepository<SalaryExpectation, Long> {
}
