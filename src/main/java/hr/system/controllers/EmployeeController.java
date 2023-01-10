package hr.system.controllers;

import hr.system.entities.Employee;
import hr.system.entities.Salary;
import hr.system.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("{id}")
    public Employee modifyEmployee(@RequestBody Employee updatedEmployee, @PathVariable UUID id) {
        return employeeService.modifyEmployee(updatedEmployee, id);
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("{id}")
    public Employee getEmployeeInfo(@PathVariable UUID id) {
        return employeeService.getEmployeeInfo(id);
    }

    @GetMapping("{id}/salary")
    public Salary getEmployeeSalary(@PathVariable UUID id) {
        return employeeService.getEmployeeSalary(id);
    }
}
