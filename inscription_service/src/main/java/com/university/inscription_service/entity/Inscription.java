package com.university.inscription_service.entity;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;    // ID de l'étudiant
    private Long formationId;  // ID de la formation
    private Long moduleId;     // ID du module (nullable si inscription à la formation seulement)
    // AJOUTEZ CE CHAMP
   // Ajout du formatage JSON pour la compatibilité avec le navigateur
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;


    public Inscription() {}

    // Constructeur complet
    public Inscription(Long studentId, Long formationId, Long moduleId, LocalDate registrationDate) {
        this.studentId = studentId;
        this.formationId = formationId;
        this.moduleId = moduleId;
        this.registrationDate = registrationDate;
    }
    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getFormationId() { return formationId; }
    public void setFormationId(Long formationId) { this.formationId = formationId; }
    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }
    public java.time.LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(java.time.LocalDate registrationDate) { this.registrationDate = registrationDate; }


}
