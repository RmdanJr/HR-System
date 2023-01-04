package hr.system.entities;

import hr.system.utils.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.Date;
import java.util.List;

@Entity
public class TeamLead extends Employee {
    @OneToOne(mappedBy = "lead")
    private Team managedTeam;

    public TeamLead() {
    }

    public TeamLead(Team managedTeam) {
        this.managedTeam = managedTeam;
    }

    public TeamLead(String name, Date birthDate, Gender gender, Date graduationDate, Salary salary,
                    List<Expertise> expertises, Department department, Team team, Team managedTeam) {
        super(name, birthDate, gender, graduationDate, salary, expertises, department, team);
        this.managedTeam = managedTeam;
    }

    public Team getManagedTeam() {
        return managedTeam;
    }

    public void setManagedTeam(Team managedTeam) {
        this.managedTeam = managedTeam;
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
