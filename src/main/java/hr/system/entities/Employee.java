package hr.system.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import hr.system.utils.Gender;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    protected UUID id;
    protected String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected Date birthDate;
    protected Gender gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected Date graduationDate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "gross", column = @Column(name = "gross_salary")),
            @AttributeOverride(name = "insuranceAmount", column = @Column(name = "insurance_amount")),
            @AttributeOverride(name = "net", column = @Column(name = "net_salary")),
    })
    protected Salary salary;
    @ManyToOne
    @JoinColumn(name = "department_id")
    protected Department department;
    @ManyToOne
    @JoinColumn(name = "team_id")
    protected Team team;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Expertise> expertises;

    public Employee() {
    }

    public Employee(String name, Date birthDate, Gender gender, Date graduationDate, Salary salary,
                    List<Expertise> expertises, Department department, Team team) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.graduationDate = graduationDate;
        this.salary = salary;
        this.expertises = expertises;
        this.department = department;
        this.team = team;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return getId().equals(employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getExpertises());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", graduationDate=" + graduationDate +
                ", salary=" + salary +
                ", expertises=" + expertises +
                ", department=" + department +
                ", team=" + team +
                '}';
    }
}
