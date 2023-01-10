package hr.system.services;

import hr.system.entities.Employee;
import hr.system.entities.Team;
import hr.system.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {
    @Autowired
    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getTeamMembers(UUID id) {
        Optional<Team> team = this.repository.findById(id);
        return team.get().getMembers();
    }
}
