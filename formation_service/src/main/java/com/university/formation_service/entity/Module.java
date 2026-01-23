package com.university.formation_service.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int maxStudents;
    private String prerequisites;

    // Dans Module.java
@ManyToOne
@JoinColumn(name = "formation_id")
@JsonIgnore // Ajoute cette annotation ici pour couper la boucle
private Formation formation;

    public Module() {}

    public Module(String name, int maxStudents, String prerequisites, Formation formation) {
        this.name = name;
        this.maxStudents = maxStudents;
        this.prerequisites = prerequisites;
        this.formation = formation;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }

    public String getPrerequisites() { return prerequisites; }
    public void setPrerequisites(String prerequisites) { this.prerequisites = prerequisites; }

    public Formation getFormation() { return formation; }
    public void setFormation(Formation formation) { this.formation = formation; }
}
