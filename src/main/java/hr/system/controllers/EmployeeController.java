package hr.system.controllers;

import hr.system.dtos.EmployeeDTO;
import hr.system.entities.Salary;
import hr.system.mappers.EmployeeMapper;
import hr.system.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;
    @Autowired
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getEmployees().stream()
                .map(employeeMapper::convertToDto)
                .toList();
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("{id}")
    public EmployeeDTO getEmployeeInfo(@PathVariable UUID id) {
        return employeeMapper.convertToDto(employeeService.getEmployeeInfo(id));
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("{id}/salary")
    public Salary getEmployeeSalary(@PathVariable UUID id) {
        return employeeService.getEmployeeSalary(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public boolean addEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.addEmployee(employee);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("{id}")
    public boolean modifyEmployee(@RequestBody EmployeeDTO updatedEmployeeDTO, @PathVariable UUID id) {
        return employeeService.modifyEmployee(updatedEmployeeDTO, id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("{id}")
    public boolean deleteEmployee(@PathVariable UUID id) {
        return employeeService.deleteEmployee(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("manager/{id}")
    public List<EmployeeDTO> getManagedEmployees(@PathVariable UUID id) {
        return employeeService.getManagedEmployees(id).stream()
                .map(employeeMapper::convertToDto).collect(Collectors.toList());
    }
}
