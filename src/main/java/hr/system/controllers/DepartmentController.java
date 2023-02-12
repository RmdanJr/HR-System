package hr.system.controllers;

import hr.system.dtos.DepartmentDTO;
import hr.system.mappers.DepartmentMapper;
import hr.system.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private final DepartmentService departmentService;

    @Autowired
    private final DepartmentMapper departmentMapper;

    public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @GetMapping
    public List<DepartmentDTO> getDepartments() {
        return departmentService.getDepartments().stream().map(departmentMapper::convertToDto).toList();
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("{id}")
    public DepartmentDTO getDepartment(@PathVariable UUID id) {
        return departmentMapper.convertToDto(departmentService.getDepartment(id));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public boolean addDepartment(@RequestBody DepartmentDTO department) {
        return departmentService.addDepartment(department);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("{id}")
    public boolean modifyDepartment(@RequestBody DepartmentDTO updatedDepartment, @PathVariable UUID id) {
        return departmentService.modifyDepartment(updatedDepartment, id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("{id}")
    public boolean deleteDepartment(@PathVariable UUID id) {
        return departmentService.deleteDepartment(id);
    }
}
