package hr.system.mappers;

import hr.system.dtos.TeamDTO;
import hr.system.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    @Autowired
    private final IdAndNameMapper idAndNameMapper;

    public TeamMapper(IdAndNameMapper idAndNameMapper) {
        this.idAndNameMapper = idAndNameMapper;
    }

    public TeamDTO convertToDto(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        if (team == null) return teamDTO;
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setDepartment(idAndNameMapper.convertToDto(team.getDepartment()));
        teamDTO.setLead(idAndNameMapper.convertToDto(team.getLead()));
        teamDTO.setMembers(team.getMembers().stream().map(idAndNameMapper::convertToDto).toList());
        return teamDTO;
    }
}
