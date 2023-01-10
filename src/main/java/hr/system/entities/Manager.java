package hr.system.entities;

import hr.system.utils.Gender;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Manager extends Employee {
    private String roles = "ROLE_EMPLOYEE,ROLE_MANAGER";
    @OneToMany
    private List<Employee> managedEmployees;

    public Manager() {
    }

    public Manager(String username, String password, String name, Date birthDate, Gender gender,
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

    @Override
    public String getRoles() {
        return roles;
    }

    @Override
    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<Employee> getManagedEmployees() {
        return managedEmployees;
    }

    public void setManagedEmployees(List<Employee> managedEmployees) {
        this.managedEmployees = managedEmployees;
    }
}