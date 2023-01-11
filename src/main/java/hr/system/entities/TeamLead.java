package hr.system.entities;

import hr.system.utils.Gender;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
public class TeamLead extends Manager {
    private String roles = "ROLE_EMPLOYEE,ROLE_MANAGER,ROLE_TEAM_LEAD";
    @OneToOne
    private Team managedTeam;

    public TeamLead() {
    }

    public TeamLead(String username, String password, String name, Date birthDate, Gender gender,
                    Date graduationDate, Salary salary, List<Expertise> expertises) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.graduationDate = graduationDate;
        this.salary = salary;
        this.expertises = expertises;
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
