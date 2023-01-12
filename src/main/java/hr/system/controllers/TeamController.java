package hr.system.controllers;

import hr.system.dtos.EmployeeDTO;
import hr.system.mappers.EmployeeMapper;
import hr.system.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    @Autowired
    private final TeamService teamService;
    @Autowired
    private final EmployeeMapper employeeMapper;

    public TeamController(TeamService teamService, EmployeeMapper employeeMapper) {
        this.teamService = teamService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("{id}/members")
    List<EmployeeDTO> getAllMembers(@PathVariable UUID id) {
        return teamService.getTeamMembers(id).stream()
                .map(employeeMapper::convertToDto).collect(Collectors.toList());
    }
}
