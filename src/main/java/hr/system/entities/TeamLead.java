package hr.system.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class TeamLead extends Manager {
    private String roles = "ROLE_EMPLOYEE,ROLE_MANAGER,ROLE_TEAM_LEAD";
    @OneToOne(mappedBy = "lead")
    private Team managedTeam;

    public TeamLead() {
    }

    public Team getManagedTeam() {
        return managedTeam;
    }

    public void setManagedTeam(Team managedTeam) {
        this.managedTeam = managedTeam;
    }

    @Override
    public String getRoles() {
        return roles;
    }

    @Override
    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "TeamLead{" +
                "managedTeam=" + managedTeam +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", graduationDate=" + graduationDate +
                ", salary=" + salary +
                ", department=" + department +
                ", team=" + team +
                '}';
    }
}
