package com.university.formation_service.repository;


import com.university.formation_service.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List; // N'oublie pas d'importer List

public interface FormationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByNameContainingIgnoreCase(String name);
}
