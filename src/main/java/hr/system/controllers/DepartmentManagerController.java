package hr.system.controllers;

import hr.system.dtos.EmployeeDTO;
import hr.system.mappers.EmployeeMapper;
import hr.system.services.DepartmentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department/manager")
public class DepartmentManagerController {
    @Autowired
    private final DepartmentManagerService managerService;
    @Autowired
    private final EmployeeMapper employeeMapper;

    public DepartmentManagerController(DepartmentManagerService managerService, EmployeeMapper employeeMapper) {
        this.managerService = managerService;
        this.employeeMapper = employeeMapper;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("{id}/employees")
    public List<EmployeeDTO> getManagedDepartmentEmployees(@PathVariable UUID id) {
        return managerService.getManagedDepartmentEmployees(id).stream()
                .map(employeeMapper::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping("{id}")
    public void deleteDepartmentManager(@PathVariable UUID id) {
        managerService.deleteDepartmentManager(id);
    }
}
