package hr.system.controllers;

import hr.system.entities.Employee;
import hr.system.services.DepartmentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/manager")
public class DepartmentManagerController {
    @Autowired
    private final DepartmentManagerService managerService;

    public DepartmentManagerController(DepartmentManagerService managerService) {
        this.managerService = managerService;
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("{id}/employees")
    public List<Employee> getManagedDepartmentEmployees(@PathVariable UUID id) {
        return managerService.getManagedDepartmentEmployees(id);
    }
}
