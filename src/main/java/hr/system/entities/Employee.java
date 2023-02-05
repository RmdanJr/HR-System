package hr.system.entities;

import hr.system.utils.interfaces.hasIdAndName;
import hr.system.utils.types.Gender;

import javax.persistence.*;
import java.util.*;

@Entity
public class Employee implements hasIdAndName {
    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id;
    private String username;
    private String password;
    private String name;
    private String roles = "ROLE_EMPLOYEE";
    private Date birthDate;
    private Gender gender = Gender.NOT_RESPONDED;
    private Date graduationDate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "gross", column = @Column(name = "GROSS_SALARY")),
            @AttributeOverride(name = "insuranceAmount", column = @Column(name = "INSURANCE_AMOUNT")),
            @AttributeOverride(name = "net", column = @Column(name = "NET_SALARY")),
    })
    private Salary salary;
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "EXPERTISE_ID")
    private List<Expertise> expertises = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;
    @OneToOne
    @JoinColumn(name = "MANAGED_DEPARTMENT_ID")
    private Department managedDepartment;
    @OneToOne
    @JoinColumn(name = "MANAGED_TEAM_ID")
    private Team managedTeam;
    @OneToMany(mappedBy = "manager")
    private List<Employee> managedEmployees = new ArrayList<>();

    public Employee() {
    }

    public Employee(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
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

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
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

    public Department getManagedDepartment() {
        return managedDepartment;
    }

    public void setManagedDepartment(Department managedDepartment) {
        this.managedDepartment = managedDepartment;
    }

    public Team getManagedTeam() {
        return managedTeam;
    }

    public void setManagedTeam(Team managedTeam) {
        this.managedTeam = managedTeam;
    }

    public List<Employee> getManagedEmployees() {
        return managedEmployees;
    }

    public void setManagedEmployees(List<Employee> managedEmployees) {
        this.managedEmployees = managedEmployees;
    }

    public void addManagedEmployee(Employee employee) {
        this.managedEmployees.add(employee);
    }

    public void addMultipleManagedEmployees(List<Employee> employees) {
        this.managedEmployees.addAll(employees);
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
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", graduationDate=" + graduationDate +
                ", salary=" + salary.getGross() +
                ", department=" + department.getName() +
                ", team=" + team.getName() +
                ", expertises=" + expertises +
                ", manager=" + manager.getName() +
                ", roles='" + roles + '\'' +
                managedDepartment == null ? "" : ", managedDepartment=" + managedDepartment.getName() +
                managedTeam == null ? "" : ", managedDepartment=" + managedTeam.getName() +
                managedEmployees == null ? "" : ", managedDepartment=" + managedEmployees.stream().map(Employee::getName).toList() +
                '}';
    }
}
