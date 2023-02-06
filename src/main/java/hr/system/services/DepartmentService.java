package hr.system.services;

import hr.system.entities.Department;
import hr.system.repositories.DepartmentRepository;
import hr.system.utils.exceptions.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {
    @Autowired
    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getDepartments() {
        return repository.findAll();
    }

    public Department getDepartment(UUID id) {
        return repository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    public Department addDepartment(Department department) {
        return repository.save(department);
    }

    public Department modifyDepartment(Department updatedDepartment, UUID id) {
        updatedDepartment.setId(id);
        return repository.save(updatedDepartment);
    }

    @Transactional
    public boolean deleteDepartment(UUID id) {
        Optional<Department> department = repository.findById(id);
        if (department.isEmpty()) return false;
        department.get().setManager(null);
        repository.delete(department.get());
        return true;
    }
}
