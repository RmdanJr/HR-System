package hr.system.controllers;

import hr.system.dtos.EmployeeDTO;
import hr.system.entities.Employee;
import hr.system.entities.Salary;
import hr.system.mappers.EmployeeMapper;
import hr.system.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("{id}")
    public EmployeeDTO getEmployeeInfo(@PathVariable UUID id) {
        return employeeMapper.convertToDto(employeeService.getEmployeeInfo(id));
    }

    @GetMapping("{id}/salary")
    public Salary getEmployeeSalary(@PathVariable UUID id) {
        return employeeService.getEmployeeSalary(id);
    }

    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody Employee employee) {
        return employeeMapper.convertToDto(employeeService.addEmployee(employee));
    }

    @PutMapping("{id}")
    public EmployeeDTO modifyEmployee(@RequestBody Employee updatedEmployee, @PathVariable UUID id) {
        return employeeMapper.convertToDto(employeeService.modifyEmployee(updatedEmployee, id));
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
    }

    //    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("manager/{id}")
    public List<EmployeeDTO> getManagedEmployees(@PathVariable UUID id) {
        return employeeService.getManagedEmployees(id).stream()
                .map(employeeMapper::convertToDto).collect(Collectors.toList());
    }
}
