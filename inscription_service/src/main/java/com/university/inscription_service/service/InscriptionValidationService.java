package com.university.inscription_service.service;

import com.university.inscription_service.entity.Inscription;
import com.university.inscription_service.repository.InscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

// Imports pour la résilience
// Vérifiez bien ces trois lignes :
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // <-- C'est cette ligne qui corrige le rouge

@Service
public class InscriptionValidationService {

    private final RestTemplate restTemplate;
    private final InscriptionRepository inscriptionRepository;
    private static final Logger logger = LoggerFactory.getLogger(InscriptionValidationService.class);

    public InscriptionValidationService(InscriptionRepository inscriptionRepository) {
        this.restTemplate = new RestTemplate();
        this.inscriptionRepository = inscriptionRepository;
    }

    // --- FORMATION ---
    @CircuitBreaker(name = "formationService", fallbackMethod = "fallbackIsFormationFull")
public boolean isFormationFull(Long formationId) {
        try {
            // 1. Récupérer les détails de la formation via le service formation (utilise le nom du service Docker)
            String formationUrl = "http://formation-service:8080/formations/" + formationId;
            FormationDTO formation = restTemplate.getForObject(formationUrl, FormationDTO.class);

            if (formation == null) {
                logger.warn("Formation {} introuvable, on bloque l'inscription.", formationId);
                return true;
            }

            // 2. Compter les inscriptions existantes via le repository local
            int currentCount = inscriptionRepository.findByFormationId(formationId).size();
            int maxPlaces = formation.getMaxStudents();

            logger.info("VÉRIFICATION : Formation {} -> {}/{} places occupées", formationId, currentCount, maxPlaces);

            return currentCount >= maxPlaces;
        } catch (Exception e) {
            throw e;
        }
    }

    // --- MÉTHODE DE SECOURS (FALLBACK) POUR FORMATION ---
    public boolean fallbackIsFormationFull(Long formationId, Throwable t) {
        logger.error("ALERTE : Service Formation injoignable pour l'ID {}. Détail : {}", formationId, t.getMessage());
        // On lance une exception 503 pour dire que le SERVICE est en panne
        throw new ResponseStatusException(
            HttpStatus.SERVICE_UNAVAILABLE, 
            "Le service de formation est indisponible. Impossible de vérifier les places."
        );
    }


@CircuitBreaker(name = "formationService", fallbackMethod = "fallbackIsModuleFull")
public boolean isModuleFull(Long moduleId) {
        try {
            String moduleUrl = "http://formation-service:8080/modules/" + moduleId;
            ModuleDTO module = restTemplate.getForObject(moduleUrl, ModuleDTO.class);

            if (module == null) return true;

            int currentCount = inscriptionRepository.findByModuleId(moduleId).size();

            logger.info("VÉRIFICATION : Module {} -> {}/{} places occupées", moduleId, currentCount, module.getMaxStudents());

            return currentCount >= module.getMaxStudents();
        } catch (Exception e) {
            throw e;
        }
    }

    // --- MÉTHODE DE SECOURS (FALLBACK) POUR MODULE ---
    public boolean fallbackIsModuleFull(Long moduleId, Throwable t) {
        logger.error("ALERTE : Service Module injoignable pour l'ID {}. Détail : {}", moduleId, t.getMessage());
        throw new ResponseStatusException(
            HttpStatus.SERVICE_UNAVAILABLE, 
            "Le service de module est indisponible actuellement."
        );
    }

    // --- ÉTUDIANT ---
@CircuitBreaker(name = "studentService", fallbackMethod = "fallbackStudentExists")
    public boolean studentExists(Long studentId) {
        String studentUrl = "http://student-service:8080/students/" + studentId;
        ResponseEntity<Object> response = restTemplate.getForEntity(studentUrl, Object.class);
        return response.getStatusCode().is2xxSuccessful();
    }

public boolean fallbackStudentExists(Long studentId, Throwable t) {
    logger.error("Le service Étudiant est injoignable pour l'ID {}. Cause: {}", studentId, t.getMessage());
    // Sécurité stricte : Si on ne peut pas vérifier, on n'autorise pas l'inscription
    return false; 
}

    // --- PREREQUIS ---
    public boolean hasPrerequisites(Long studentId, String prerequisites) {
        if(prerequisites == null || prerequisites.isEmpty()) return true;
        return true; 
    }

    // --- DTOs ---
    public static class FormationDTO {
        private Long id;
        private String name;
        private int maxStudents;
        private String prerequisites;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getMaxStudents() { return maxStudents; }
        public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
        public String getPrerequisites() { return prerequisites; }
        public void setPrerequisites(String prerequisites) { this.prerequisites = prerequisites; }
    }

    public static class ModuleDTO {
        private Long id;
        private String name;
        private int maxStudents;
        private String prerequisites;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getMaxStudents() { return maxStudents; }
        public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
        public String getPrerequisites() { return prerequisites; }
        public void setPrerequisites(String prerequisites) { this.prerequisites = prerequisites; }
    }
}