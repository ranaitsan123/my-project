package com.university.formation_service.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    private String name;
    private int maxStudents;
    private String prerequisites;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "formation_id") // Hibernate va créer cette colonne dans la table 'module'
private List<Module> modules = new ArrayList<>();


    // Constructeurs
    public Formation() {}

    public Formation(String name, int maxStudents, String prerequisites) {
        this.name = name;
        this.maxStudents = maxStudents;
        this.prerequisites = prerequisites;
    }

    public void addModule(Module module) {
        modules.add(module);
        module.setFormation(this);
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }

    public String getPrerequisites() { return prerequisites; }
    public void setPrerequisites(String prerequisites) { this.prerequisites = prerequisites; }

   // Ajoute ceci dans Formation.java
public List<Module> getModules() {
    return modules;
}


public void setModules(List<Module> modules) {
    this.modules = modules;
}
}
