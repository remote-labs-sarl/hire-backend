package com.remotelabs.hire.repositories;

import com.remotelabs.hire.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "SELECT * FROM countries WHERE active = true AND deleted_at IS NULL ORDER BY id, name", nativeQuery = true)
    List<Country> findActiveCountries();
    Optional<Country> findByCode(String code);
}
