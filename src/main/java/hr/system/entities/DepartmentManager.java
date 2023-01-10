package hr.system.entities;

import hr.system.utils.Gender;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
public class DepartmentManager extends Employee {
    @OneToOne(mappedBy = "manager")
    private Department managedDepartment;

    public DepartmentManager() {
    }

    public DepartmentManager(Department managedDepartment) {
        this.managedDepartment = managedDepartment;
    }

    public DepartmentManager(String username, String password, String roles, String name) {
        super(username, password, roles, name);
    }

    public DepartmentManager(String username, String password, String roles, String name, Date birthDate,
                             Gender gender, Date graduationDate, Salary salary,
                             List<Expertise> expertises, Department department, Team team, Department managedDepartment) {
        super(username, password, roles, name, birthDate, gender, graduationDate, salary, expertises, department, team);
        this.managedDepartment = managedDepartment;
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
