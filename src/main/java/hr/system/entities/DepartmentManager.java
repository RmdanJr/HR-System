package hr.system.entities;

import hr.system.utils.Gender;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
public class DepartmentManager extends Employee {
    private String roles = "ROLE_EMPLOYEE,ROLE_MANAGER,ROLE_DEPARTMENT_MANAGER";
    @OneToOne
    private Department managedDepartment;

    public DepartmentManager() {
    }

    public DepartmentManager(String username, String password, String name, Date birthDate, Gender gender,
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

    public Department getManagedDepartment() {
        return managedDepartment;
    }

    public void setManagedDepartment(Department managedDepartment) {
        this.managedDepartment = managedDepartment;
    }

    @Override
    public String toString() {
        return "DepartmentManager{" +
                "managedDepartment=" + managedDepartment +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
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
