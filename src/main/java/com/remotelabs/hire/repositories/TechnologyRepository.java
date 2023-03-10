package com.remotelabs.hire.repositories;

import com.remotelabs.hire.entities.TechSkill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<TechSkill, Long> {

    @Query(value = "SELECT * FROM tech_skills WHERE lower(name) LIKE CONCAT('%',:keyword,'%')" +
            " OR lower(tags) LIKE CONCAT('%',:keyword,'%') ORDER BY name ASC", nativeQuery = true)
    Page<TechSkill> findTechnologies(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM tech_skills WHERE id in (:ids)", nativeQuery = true)
    List<TechSkill> findByIds(@Param("ids") List<Long> ids);
}
