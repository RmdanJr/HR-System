package hr.system.mappers;


import hr.system.dtos.EmployeeTeamDTO;
import hr.system.entities.Team;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTeamMapper {
    public EmployeeTeamDTO convertToDto(Team team) {
        EmployeeTeamDTO employeeTeamDTO = new EmployeeTeamDTO();
        if (team != null) {
            employeeTeamDTO.setName(team.getName());
            if (team.getLead() != null) {
                employeeTeamDTO.setTeamLead(team.getLead().getName());
            }
        }
        return employeeTeamDTO;
    }
}
