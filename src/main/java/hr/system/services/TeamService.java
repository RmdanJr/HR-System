package hr.system.services;

import hr.system.dtos.TeamDTO;
import hr.system.entities.Employee;
import hr.system.entities.Team;
import hr.system.repositories.EmployeeRepository;
import hr.system.repositories.TeamRepository;
import hr.system.utils.exceptions.DepartmentNotFoundException;
import hr.system.utils.exceptions.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {
    @Autowired
    private final TeamRepository teamRepository;

    @Autowired
    private final EmployeeRepository employeeRepository;

    public TeamService(TeamRepository teamRepository, EmployeeRepository employeeRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getTeamMembers(UUID id) {
        Team team = this.teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        return team.getMembers();
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public Team getTeam(UUID id) {
        return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }

    public boolean addTeam(TeamDTO teamDTO) {
        try {
            Team newTeam = teamRepository.save(new Team(teamDTO.getName()));
            if (teamDTO.getLead() != null && teamDTO.getLead().getId() != null)
                employeeRepository.updateManagedTeam(teamDTO.getLead().getId(), newTeam.getId());
            if (teamDTO.getDepartment() != null && teamDTO.getDepartment().getId() != null) {
                teamRepository.updateDepartment(newTeam.getId(), teamDTO.getDepartment().getId());
            }
            teamDTO.getMembers().forEach(member -> {
                employeeRepository.updateTeam(member.getId(), newTeam.getId());
                if (teamDTO.getDepartment() != null && teamDTO.getDepartment().getId() != null)
                    employeeRepository.updateDepartment(member.getId(), teamDTO.getDepartment().getId());
                else
                    employeeRepository.updateDepartment(member.getId(), null);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modifyTeam(TeamDTO updatedTeam, UUID id) {
        try {
            Team outdatedTeam = teamRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
            teamRepository.updateName(updatedTeam.getId(), updatedTeam.getName());
            if (outdatedTeam.getLead() != null && outdatedTeam.getLead().getId() != null)
                employeeRepository.updateManagedTeam(outdatedTeam.getLead().getId(), null);
            if (updatedTeam.getLead() != null && updatedTeam.getLead().getId() != null)
                employeeRepository.updateManagedTeam(updatedTeam.getLead().getId(), updatedTeam.getId());
            teamRepository.updateDepartment(outdatedTeam.getId(), null);
            if (updatedTeam.getDepartment() != null && updatedTeam.getDepartment().getId() != null) {
                teamRepository.updateDepartment(updatedTeam.getId(), updatedTeam.getDepartment().getId());
            }
            outdatedTeam.getMembers().forEach(member -> {
                employeeRepository.updateTeam(member.getId(), null);
            });
            updatedTeam.getMembers().forEach(member -> {
                employeeRepository.updateTeam(member.getId(), updatedTeam.getId());
                if (updatedTeam.getDepartment() != null && updatedTeam.getDepartment().getId() != null)
                    employeeRepository.updateDepartment(member.getId(), updatedTeam.getDepartment().getId());
                else
                    employeeRepository.updateDepartment(member.getId(), null);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteTeam(UUID id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isEmpty()) return false;
        team.get().setLead(null);
        team.get().setDepartment(null);
        teamRepository.delete(team.get());
        return true;
    }
}
