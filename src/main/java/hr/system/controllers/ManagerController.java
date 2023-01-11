package hr.system.controllers;

import hr.system.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    @Autowired
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @DeleteMapping("{id}")
    public void deleteManager(@PathVariable UUID id) {
        managerService.deleteManager(id);
    }
}
