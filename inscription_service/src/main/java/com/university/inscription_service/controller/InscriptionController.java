package com.university.inscription_service.controller;


import com.university.inscription_service.entity.Inscription;
import com.university.inscription_service.service.InscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inscriptions")
@CrossOrigin(origins = "*")
public class InscriptionController {

    private final InscriptionService service;

    public InscriptionController(InscriptionService service) {
        this.service = service;
    }

@GetMapping("/search")
public List<Inscription> search(@RequestParam String keyword) {
    // On appelle le SERVICE et non le repository
    return service.searchInscriptions(keyword);
}

    @GetMapping
    public List<Inscription> getAll() {
        return service.getAllInscriptions();
    }

    @GetMapping("/student/{studentId}")
    public List<Inscription> getByStudent(@PathVariable Long studentId) {
        return service.getByStudent(studentId);
    }

    @GetMapping("/formation/{formationId}")
    public List<Inscription> getByFormation(@PathVariable Long formationId) {
        return service.getByFormation(formationId);
    }

    @GetMapping("/module/{moduleId}")
    public List<Inscription> getByModule(@PathVariable Long moduleId) {
        return service.getByModule(moduleId);
    }

    @PostMapping
    public Inscription create(@RequestBody Inscription inscription) {
        return service.createInscription(inscription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteInscription(id);
        return ResponseEntity.noContent().build();
    }
}
