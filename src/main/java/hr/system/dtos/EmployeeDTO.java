package hr.system.dtos;

import hr.system.entities.Expertise;
import hr.system.entities.Salary;
import hr.system.utils.types.Gender;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EmployeeDTO {
    public boolean isManager = false;
    private UUID id;
    private String username;
    private String password;
    private String name;
    private Date birthDate;
    private Gender gender;
    private Date graduationDate;
    private Salary salary;
    private List<Expertise> expertises;
    private IdAndName department;
    private IdAndName team;
    private IdAndName manager;
    private IdAndName managedDepartment;
    private IdAndName managedTeam;
    private List<IdAndName> managedEmployees = new ArrayList<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public IdAndName getDepartment() {
        return department;
    }

    public void setDepartment(IdAndName department) {
        this.department = department;
    }

    public IdAndName getTeam() {
        return team;
    }

    public void setTeam(IdAndName team) {
        this.team = team;
    }

    public IdAndName getManager() {
        return manager;
    }

    public void setManager(IdAndName manager) {
        this.manager = manager;
    }

    public IdAndName getManagedDepartment() {
        return managedDepartment;
    }

    public void setManagedDepartment(IdAndName managedDepartment) {
        this.managedDepartment = managedDepartment;
    }

    public IdAndName getManagedTeam() {
        return managedTeam;
    }

    public void setManagedTeam(IdAndName managedTeam) {
        this.managedTeam = managedTeam;
    }

    public List<IdAndName> getManagedEmployees() {
        return managedEmployees;
    }

    public void setManagedEmployees(List<IdAndName> managedEmployees) {
        this.managedEmployees = managedEmployees;
    }
}
