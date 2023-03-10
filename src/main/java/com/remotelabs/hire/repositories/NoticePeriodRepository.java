package com.remotelabs.hire.repositories;

import com.remotelabs.hire.entities.NoticePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticePeriodRepository extends JpaRepository<NoticePeriod, Long> {
}
