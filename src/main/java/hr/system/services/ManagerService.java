package hr.system.services;

import hr.system.entities.Manager;
import hr.system.repositories.ManagerRepository;
import hr.system.utils.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ManagerService {
    @Autowired
    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public void deleteManager(UUID id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        if (manager.getManager() == null) return;
        Manager directManager = manager.getManager();
        directManager.setManagedEmployees(Stream.concat(
                directManager.getManagedEmployees().stream(),
                manager.getManagedEmployees().stream()).toList());
        managerRepository.delete(manager);
        managerRepository.save(directManager);
    }
}
