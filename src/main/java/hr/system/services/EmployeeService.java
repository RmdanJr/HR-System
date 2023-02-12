package hr.system.services;

import hr.system.dtos.EmployeeDTO;
import hr.system.entities.Employee;
import hr.system.entities.Salary;
import hr.system.repositories.EmployeeRepository;
import hr.system.utils.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public boolean addEmployee(EmployeeDTO employeeDTO) {
        try {
            Employee newEmployee = new Employee(employeeDTO.getUsername(), passwordEncoder.encode(employeeDTO.getPassword()),
                    employeeDTO.getName(), employeeDTO.getBirthDate(), employeeDTO.getGender(),
                    employeeDTO.getGraduationDate(), employeeDTO.getSalary().getGross(), employeeDTO.getExpertises());
            boolean isManager = false;
            employeeRepository.save(newEmployee);
            if (employeeDTO.getDepartment() != null && employeeDTO.getDepartment().getId() != null) {
                employeeRepository.updateDepartment(newEmployee.getId(), employeeDTO.getDepartment().getId());
            }
            if (employeeDTO.getTeam() != null && employeeDTO.getTeam().getId() != null) {
                employeeRepository.updateTeam(newEmployee.getId(), employeeDTO.getTeam().getId());
            }
            if (employeeDTO.getManager() != null && employeeDTO.getManager().getId() != null) {
                employeeRepository.updateManager(newEmployee.getId(), employeeDTO.getManager().getId());
                Employee manager = employeeRepository.findById(employeeDTO.getManager().getId())
                        .orElseThrow(() -> new EmployeeNotFoundException(employeeDTO.getManager().getId()));
                manager.addRole("ROLE_MANAGER");
                employeeRepository.save(manager);
            }
            if (employeeDTO.getManagedDepartment() != null && employeeDTO.getManagedDepartment().getId() != null) {
                if (!Objects.equals(employeeDTO.getManagedDepartment().getId().toString(), "")) isManager = true;
                employeeRepository.updateManagedDepartment(newEmployee.getId(), employeeDTO.getManagedDepartment().getId());
            }
            if (employeeDTO.getManagedTeam() != null && employeeDTO.getManagedTeam().getId() != null) {
                if (!Objects.equals(employeeDTO.getManagedTeam().getId().toString(), "")) isManager = true;
                employeeRepository.updateManagedTeam(newEmployee.getId(), employeeDTO.getManagedTeam().getId());
            }
            if (!employeeDTO.getManagedEmployees().isEmpty()) isManager = true;
            employeeDTO.getManagedEmployees().forEach(employee -> {
                employeeRepository.updateManager(employee.getId(), newEmployee.getId());
            });
            if (isManager) {
                newEmployee.addRole("ROLE_MANAGER");
                employeeRepository.save(newEmployee);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modifyEmployee(EmployeeDTO updatedEmployee, UUID id) {
        try {
            Employee employee = employeeRepository.findById(updatedEmployee.getId())
                    .orElseThrow(() -> new EmployeeNotFoundException(id));
            employee.setUsername(updatedEmployee.getUsername());
            employee.setPassword(passwordEncoder.encode(updatedEmployee.getPassword()));
            employee.setName(updatedEmployee.getName());
            employee.setBirthDate(updatedEmployee.getBirthDate());
            employee.setGender(updatedEmployee.getGender());
            employee.setGraduationDate(updatedEmployee.getGraduationDate());
            employee.setSalary(new Salary(updatedEmployee.getSalary().getGross()));
            employee.setGraduationDate(updatedEmployee.getGraduationDate());
            employee.setExpertises(updatedEmployee.getExpertises());
            boolean isManager = false;
            employeeRepository.save(employee);
            employeeRepository.updateDepartment(employee.getId(), null);
            if (updatedEmployee.getDepartment() != null && updatedEmployee.getDepartment().getId() != null) {
                employeeRepository.updateDepartment(employee.getId(), updatedEmployee.getDepartment().getId());
            }
            employeeRepository.updateTeam(employee.getId(), null);
            if (updatedEmployee.getTeam() != null && updatedEmployee.getTeam().getId() != null) {
                employeeRepository.updateTeam(employee.getId(), updatedEmployee.getTeam().getId());
            }
            employeeRepository.updateManager(employee.getId(), null);
            if (updatedEmployee.getManager() != null && updatedEmployee.getManager().getId() != null) {
                employeeRepository.updateManager(employee.getId(), updatedEmployee.getManager().getId());
            }
            employeeRepository.updateManagedDepartment(employee.getId(), null);
            if (updatedEmployee.getManagedDepartment() != null && updatedEmployee.getManagedDepartment().getId() != null) {
                if (!Objects.equals(updatedEmployee.getManagedDepartment().getId().toString(), "")) isManager = true;
                employeeRepository.updateManagedDepartment(employee.getId(), updatedEmployee.getManagedDepartment().getId());
            }
            employeeRepository.updateManagedTeam(employee.getId(), null);
            if (updatedEmployee.getManagedTeam() != null && updatedEmployee.getManagedTeam().getId() != null) {
                if (!Objects.equals(updatedEmployee.getManagedTeam().getId().toString(), "")) isManager = true;
                employeeRepository.updateManagedTeam(employee.getId(), updatedEmployee.getManagedTeam().getId());
            }
            if (!updatedEmployee.getManagedEmployees().isEmpty()) isManager = true;
            employee.getManagedEmployees().forEach(managedEmployee -> {
                employeeRepository.updateManager(managedEmployee.getId(), null);
            });
            updatedEmployee.getManagedEmployees().forEach(managedEmployee -> {
                employeeRepository.updateManager(managedEmployee.getId(), employee.getId());
            });
            if (isManager && !employee.getRoles().contains("ROLE_MANAGER")) {
                employee.addRole("ROLE_MANAGER");
            }
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean deleteEmployee(UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) return false;
        if (employee.get().getManager() == null && !employee.get().getManagedEmployees().isEmpty()) return false;
        employee.get().setTeam(null);
        employee.get().setDepartment(null);
        employee.get().setManagedTeam(null);
        employee.get().setManagedDepartment(null);
        Employee directManager = employee.get().getManager();
        employee.get().getManagedEmployees().forEach(managedEmployee -> {
            managedEmployee.setManager(directManager);
        });
        employee.get().setManager(null);
        employee.get().setManagedEmployees(null);
        employeeRepository.deleteById(id);
        return true;
    }

    public Employee getEmployeeInfo(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Salary getEmployeeSalary(UUID id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return employee.getSalary();
    }

    public List<Employee> getManagedEmployees(UUID id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return employee.getManagedEmployees();
    }
}
