package com.remotelabs.hire.repositories;

import com.remotelabs.hire.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
