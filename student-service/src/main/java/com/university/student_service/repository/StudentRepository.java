package com.university.student_service.repository;


import com.university.student_service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // N'oublie pas d'importer List

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository fournit déjà CRUD (create, read, update, delete)
    List<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}
