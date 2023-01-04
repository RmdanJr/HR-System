package hr.system.entities;

import hr.system.utils.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

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

    public DepartmentManager(String name, Date birthDate, Gender gender, Date graduationDate, Salary salary,
                             List<Expertise> expertises, Department department, Team team, Department managedDepartment) {
        super(name, birthDate, gender, graduationDate, salary, expertises, department, team);
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
