package com.university.inscription_service.service;


import com.university.inscription_service.entity.Inscription;
import com.university.inscription_service.repository.InscriptionRepository;
import com.university.inscription_service.service.InscriptionValidationService.FormationDTO;
import com.university.inscription_service.service.InscriptionValidationService.ModuleDTO;

import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class InscriptionService {

    private final InscriptionRepository repository;
    private final InscriptionValidationService validationService;

    public InscriptionService(InscriptionRepository repository, InscriptionValidationService validationService) {
    this.repository = repository;
    this.validationService = validationService;
}

    public List<Inscription> getAllInscriptions() {
        return repository.findAll();
    }

    

    public List<Inscription> getByStudent(Long studentId) {
        return repository.findByStudentId(studentId);
    }

    public List<Inscription> getByFormation(Long formationId) {
        return repository.findByFormationId(formationId);
    }

    public List<Inscription> getByModule(Long moduleId) {
        return repository.findByModuleId(moduleId);
    }

    // Modifier createInscription pour ajouter la validation
public Inscription createInscription(Inscription inscription) {

    // 1. VÉRIFIER SI L'ÉTUDIANT EXISTE (Nouveau)
    if (!validationService.studentExists(inscription.getStudentId())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'étudiant n'existe pas ou vérification impossible !");
    }

    // Vérifier si formation ou module plein
    if(inscription.getModuleId() != null) {
        if(validationService.isModuleFull(inscription.getModuleId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Module plein !");
    } else {
        if(validationService.isFormationFull(inscription.getFormationId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La Formation est déja pleine !");
    }

    // Vérifier les prérequis
   String prereq = "";
    if(inscription.getModuleId() != null) {
        // Appel au microservice module (nom du service Docker)
        String moduleUrl = "http://formation-service:8080/modules/" + inscription.getModuleId();
        ModuleDTO module = new RestTemplate().getForObject(moduleUrl, ModuleDTO.class);
        prereq = module != null ? module.getPrerequisites() : "";
    } else {
        // Appel au microservice formation (nom du service Docker)
        String formationUrl = "http://formation-service:8080/formations/" + inscription.getFormationId();
        FormationDTO formation = new RestTemplate().getForObject(formationUrl, FormationDTO.class);
        prereq = formation != null ? formation.getPrerequisites() : "";
    }


    if(!validationService.hasPrerequisites(inscription.getStudentId(), prereq))
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prérequis non remplis !");

    return repository.save(inscription);
}

    public void deleteInscription(Long id) {
        repository.deleteById(id);
    }

   public List<Inscription> searchInscriptions(String keyword) {
    // On renvoie tout, c'est le JavaScript qui fera le tri
    return repository.findAll();
}
    
}
