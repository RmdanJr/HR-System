package hr.system.dtos;

import java.util.UUID;

public class EmployeeTeamDTO {
    private UUID id;
    private String name;
    private String teamLead;

    public EmployeeTeamDTO() {
        this.name = "";
        this.teamLead = "";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(String teamLead) {
        this.teamLead = teamLead;
    }
}
