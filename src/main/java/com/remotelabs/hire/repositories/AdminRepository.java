package com.remotelabs.hire.repositories;

import com.remotelabs.hire.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
