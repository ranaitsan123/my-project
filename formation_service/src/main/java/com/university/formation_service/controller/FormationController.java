package com.university.formation_service.controller;



import com.university.formation_service.entity.Formation;
import com.university.formation_service.service.FormationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.university.formation_service.entity.Module; // OUI
// import java.lang.Module; <--- NON (à supprimer si présent)
@RestController
@RequestMapping("/formations")
@CrossOrigin(origins = "*")
public class FormationController {

    private final FormationService service;

    public FormationController(FormationService service) {
        this.service = service;
    }

    // Recherche de formations par mot-clé
    @GetMapping("/search")
    public List<Formation> searchFormations(@RequestParam String keyword) {
        // Cette méthode va appeler le service pour filtrer les noms
        return service.searchFormations(keyword);
    }

    @GetMapping
    public List<Formation> getAllFormations() {
        return service.getAllFormations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) {
        Formation f = service.getFormationById(id);
        if (f != null) return ResponseEntity.ok(f);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Formation createFormation(@RequestBody Formation formation) {
        return service.saveFormation(formation);
    }

    @PutMapping("/{id}")
public ResponseEntity<Formation> updateFormation(@PathVariable Long id, @RequestBody Formation formation) {
    Formation existing = service.getFormationById(id);
    if (existing == null) return ResponseEntity.notFound().build();

    existing.setName(formation.getName());
    existing.setMaxStudents(formation.getMaxStudents());
    existing.setPrerequisites(formation.getPrerequisites());

    if (formation.getModules() != null) {
        existing.getModules().clear();
        // Utilisez le nom de classe avec son package pour éviter la confusion avec java.lang.Module
        for (com.university.formation_service.entity.Module m : formation.getModules()) {
            existing.addModule(m); 
        }
    }

    service.saveFormation(existing);
    return ResponseEntity.ok(existing);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
    Formation existing = service.getFormationById(id);
    if (existing == null) {
        return ResponseEntity.notFound().build();
    }
    service.deleteFormation(id); // Appel de la méthode de suppression dans le service
    return ResponseEntity.noContent().build();
}


}

