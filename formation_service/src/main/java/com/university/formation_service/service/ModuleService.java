package com.university.formation_service.service;


import com.university.formation_service.entity.Module;
import com.university.formation_service.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    private final ModuleRepository repository;

    public ModuleService(ModuleRepository repository) {
        this.repository = repository;
    }

    public List<Module> getModulesByFormation(Long formationId) {
        return repository.findByFormationId(formationId);
    }

    public Module saveModule(Module module) {
        return repository.save(module);
    }

    public void deleteModule(Long id) {
        repository.deleteById(id);
    }
}
