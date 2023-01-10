package hr.system.services;

import hr.system.entities.Employee;
import hr.system.entities.Salary;
import hr.system.repositories.EmployeeRepository;
import hr.system.utils.EmployeeNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public EmployeeService() {
    }

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeRepository getRepository() {
        return repository;
    }

    public void setRepository(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Transactional
    public Employee modifyEmployee(@NotNull Employee updatedEmployee, UUID id) {
        repository.deleteById(id);
        updatedEmployee.setId(id);
        return repository.save(updatedEmployee);
    }

    public void deleteEmployee(UUID id) {
        repository.deleteById(id);
    }

    public Employee getEmployeeInfo(UUID id) {
        try {
            return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Salary getEmployeeSalary(UUID id) {
        try {
            Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
            return employee.getSalary();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
