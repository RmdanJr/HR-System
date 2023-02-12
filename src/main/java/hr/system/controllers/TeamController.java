package hr.system.controllers;

import hr.system.dtos.EmployeeDTO;
import hr.system.dtos.TeamDTO;
import hr.system.mappers.EmployeeMapper;
import hr.system.mappers.TeamMapper;
import hr.system.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private final TeamService teamService;
    @Autowired
    private final EmployeeMapper employeeMapper;
    @Autowired
    private final TeamMapper teamMapper;

    public TeamController(TeamService teamService, EmployeeMapper employeeMapper, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.employeeMapper = employeeMapper;
        this.teamMapper = teamMapper;
    }

    @GetMapping
    public List<TeamDTO> getTeams() {
        return teamService.getTeams().stream().map(teamMapper::convertToDto).toList();
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("{id}")
    public TeamDTO getTeam(@PathVariable UUID id) {
        return teamMapper.convertToDto(teamService.getTeam(id));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public boolean addTeam(@RequestBody TeamDTO team) {
        return teamService.addTeam(team);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("{id}")
    public boolean modifyTeam(@RequestBody TeamDTO updatedTeam, @PathVariable UUID id) {
        return teamService.modifyTeam(updatedTeam, id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("{id}")
    public boolean deleteTeam(@PathVariable UUID id) {
        return teamService.deleteTeam(id);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("{id}/members")
    List<EmployeeDTO> getAllMembers(@PathVariable UUID id) {
        return teamService.getTeamMembers(id).stream()
                .map(employeeMapper::convertToDto).collect(Collectors.toList());
    }
}
