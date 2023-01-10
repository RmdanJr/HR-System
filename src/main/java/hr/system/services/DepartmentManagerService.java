package hr.system.services;

import hr.system.entities.DepartmentManager;
import hr.system.entities.Employee;
import hr.system.repositories.DepartmentManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentManagerService {
    @Autowired
    private final DepartmentManagerRepository repository;

    public DepartmentManagerService(DepartmentManagerRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getManagedDepartmentEmployees(UUID id) {
        DepartmentManager manager = repository.findById(id).get();
        List<Employee> employees = manager.getManagedDepartment().getEmployees();
        return employees;
    }
}
