package hr.system.services;

import hr.system.repositories.TeamLeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeamLeadService {
    @Autowired
    private final TeamLeadRepository teamLeadRepository;

    public TeamLeadService(TeamLeadRepository teamLeadRepository) {
        this.teamLeadRepository = teamLeadRepository;
    }

    public void deleteTeamLead(UUID id) {
        teamLeadRepository.deleteById(id);
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
//        if (manager.getManager() == null) return;
//        Manager directManager = manager.getManager();
//        for (Employee employee : manager.getManagedEmployees()) {
//            employee.setManager(directManager);
//        }
//        managerRepository.delete(manager);
    }
}
