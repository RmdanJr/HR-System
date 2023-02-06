package hr.system.services;

import hr.system.entities.Employee;
import hr.system.entities.Salary;
import hr.system.repositories.EmployeeRepository;
import hr.system.utils.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
    public boolean deleteEmployee(UUID id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) return false;
        if (employee.get().getManager() == null && !employee.get().getManagedEmployees().isEmpty()) return false;
        employee.get().setTeam(null);
        employee.get().setDepartment(null);
        employee.get().setManagedTeam(null);
        employee.get().setManagedDepartment(null);
        Employee directManager = employee.get().getManager();
        employee.get().getManagedEmployees().forEach(managedEmployee -> {
            managedEmployee.setManager(directManager);
        });
        employee.get().setManager(null);
        employee.get().setManagedEmployees(null);
        repository.deleteById(id);
        return true;
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
