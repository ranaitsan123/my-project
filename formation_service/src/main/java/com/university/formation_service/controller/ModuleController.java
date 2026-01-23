package com.university.formation_service.controller;


import com.university.formation_service.entity.Formation;
import com.university.formation_service.entity.Module;
import com.university.formation_service.service.FormationService;
import com.university.formation_service.service.ModuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
@CrossOrigin("*")
public class ModuleController {

    private final ModuleService moduleService;
    private final FormationService formationService;

    public ModuleController(ModuleService moduleService, FormationService formationService) {
        this.moduleService = moduleService;
        this.formationService = formationService;
    }

    @GetMapping("/formation/{formationId}")
    public List<Module> getModulesByFormation(@PathVariable Long formationId) {
        return moduleService.getModulesByFormation(formationId);
    }

    @PostMapping("/formation/{formationId}")
    public Module addModule(
            @PathVariable Long formationId,
            @RequestBody Module module) {

        Formation formation = formationService.getFormationById(formationId);
        module.setFormation(formation);
        return moduleService.saveModule(module);
    }
}
