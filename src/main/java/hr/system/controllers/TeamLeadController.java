package hr.system.controllers;

import hr.system.services.TeamLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/team/lead/")
public class TeamLeadController {
    @Autowired
    private final TeamLeadService teamLeadService;

    public TeamLeadController(TeamLeadService teamLeadService) {
        this.teamLeadService = teamLeadService;
    }

    @DeleteMapping("{id}")
    public void deleteTeamLead(@PathVariable UUID id) {
        teamLeadService.deleteTeamLead(id);
    }
}
