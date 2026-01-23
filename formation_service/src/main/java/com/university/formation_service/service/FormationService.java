package com.university.formation_service.service;



import com.university.formation_service.entity.Formation;
import com.university.formation_service.repository.FormationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationService {

    private final FormationRepository repository;

    public FormationService(FormationRepository repository) {
        this.repository = repository;
    }

    public List<Formation> getAllFormations() {
        return repository.findAll();
    }

    public Formation getFormationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    

    public void deleteFormation(Long id) {
        repository.deleteById(id);
    }

    // Dans FormationService.java
public List<Formation> searchFormations(String keyword) {
    return repository.findByNameContainingIgnoreCase(keyword);
}
// Dans FormationService.java
public Formation saveFormation(Formation formation) {
    if (formation.getModules() != null) {
        for (com.university.formation_service.entity.Module module : formation.getModules()) {
            module.setFormation(formation);
        }
    }
    return repository.save(formation); // Assurez-vous que 'repository' est bien le nom de votre variable
}
}

