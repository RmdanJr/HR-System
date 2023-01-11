package hr.system.dtos;

import hr.system.entities.Expertise;
import hr.system.entities.Salary;
import hr.system.utils.Gender;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EmployeeDTO {
    private UUID id;
    private String username;
    private String name;
    private Date birthDate;
    private Gender gender;
    private Date graduationDate;
    private Salary salary;
    private List<Expertise> expertises;
    private EmployeeDepartmentDTO department;
    private EmployeeTeamDTO team;
    private String manager;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public List<Expertise> getExpertises() {
        return expertises;
    }

    public void setExpertises(List<Expertise> expertises) {
        this.expertises = expertises;
    }

    public EmployeeDepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(EmployeeDepartmentDTO department) {
        this.department = department;
    }

    public EmployeeTeamDTO getTeam() {
        return team;
    }

    public void setTeam(EmployeeTeamDTO team) {
        this.team = team;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
