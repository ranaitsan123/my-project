package com.university.inscription_service.repository;


import com.university.inscription_service.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    List<Inscription> findByStudentId(Long studentId);
    List<Inscription> findByFormationId(Long formationId);
    List<Inscription> findByModuleId(Long moduleId);


    
}
