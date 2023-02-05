package hr.system.controllers;

import hr.system.dtos.DepartmentDTO;
import hr.system.entities.Department;
import hr.system.mappers.DepartmentMapper;
import hr.system.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("{id}")
    public DepartmentDTO getDepartment(UUID id) {
        return departmentMapper.convertToDto(departmentService.getDepartment(id));
    }

    @PostMapping
    public DepartmentDTO addDepartment(@RequestBody Department department) {
        return departmentMapper.convertToDto(departmentService.addDepartment(department));
    }

    @PutMapping("{id}")
    public DepartmentDTO modifyDepartment(@RequestBody Department updatedDepartment, @PathVariable UUID id) {
        return departmentMapper.convertToDto(departmentService.modifyDepartment(updatedDepartment, id));
    }

    @DeleteMapping("{id}")
    public void deleteDepartment(@PathVariable UUID id) {
        departmentService.deleteDepartment(id);
    }
}
