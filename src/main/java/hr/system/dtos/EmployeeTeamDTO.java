package hr.system.dtos;

public class EmployeeTeamDTO {
    private String name;
    private String teamLead;

    public EmployeeTeamDTO() {
        this.name = "";
        this.teamLead = "";
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
