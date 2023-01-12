package hr.system.services;

import hr.system.entities.Employee;
import hr.system.entities.Salary;
import hr.system.repositories.EmployeeRepository;
import hr.system.utils.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee modifyEmployee(Employee updatedEmployee, UUID id) {
        updatedEmployee.setId(id);
        return repository.save(updatedEmployee);
    }

    @Transactional
    public void deleteEmployee(UUID id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        if(employee.getManager() == null && !employee.getManagedEmployees().isEmpty()) return;
        employee.setTeam(null);
        employee.setDepartment(null);
        employee.setManagedTeam(null);
        employee.setManagedDepartment(null);
        Employee directManager = employee.getManager();
        employee.getManagedEmployees().forEach(managedEmployee -> {
            managedEmployee.setManager(directManager);
        });
        employee.setManager(null);
        employee.setManagedEmployees(null);
        repository.deleteById(id);
    }

    public Employee getEmployeeInfo(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Salary getEmployeeSalary(UUID id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return employee.getSalary();
    }

    public List<Employee> getManagedEmployees(UUID id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return employee.getManagedEmployees();
    }
}
