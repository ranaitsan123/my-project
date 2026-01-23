package com.university.formation_service.repository;


import com.university.formation_service.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByFormationId(Long formationId);
}
