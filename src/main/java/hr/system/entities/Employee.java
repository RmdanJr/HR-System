package hr.system.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import hr.system.utils.Gender;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee {
    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "uuid")
    protected UUID id;
    protected String username;
    protected String password;
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
    protected Department department;
    @ManyToOne
    protected Team team;
    @OneToMany(cascade = CascadeType.ALL)
    protected List<Expertise> expertises;
    @ManyToOne
    protected Manager manager;
    private String roles = "ROLE_EMPLOYEE";

    public Employee() {
    }

    public Employee(String username, String password, String name, Date birthDate, Gender gender,
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return getId().equals(employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
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
                ", expertises=" + expertises +
                '}';
    }
}
