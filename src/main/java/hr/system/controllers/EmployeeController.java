package hr.system.controllers;

import hr.system.entities.Employee;
import hr.system.entities.Salary;
import hr.system.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee modifyEmployee(@RequestBody Employee updatedEmployee, @PathVariable UUID id) {
        return employeeService.modifyEmployee(updatedEmployee, id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeInfo(@PathVariable UUID id) {
        return employeeService.getEmployeeInfo(id);
    }

    @GetMapping("/employees/{id}/salary")
    public Salary getEmployeeSalary(@PathVariable UUID id) {
        return employeeService.getEmployeeSalary(id);
    }
}
