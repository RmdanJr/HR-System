package hr.system.services;

import hr.system.dtos.DepartmentDTO;
import hr.system.entities.Department;
import hr.system.entities.Employee;
import hr.system.repositories.DepartmentRepository;
import hr.system.repositories.EmployeeRepository;
import hr.system.repositories.TeamRepository;
import hr.system.utils.exceptions.DepartmentNotFoundException;
import hr.system.utils.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {
    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final TeamRepository teamRepository;

    public DepartmentService(DepartmentRepository departmentRepository,
                             EmployeeRepository employeeRepository, TeamRepository teamRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(UUID id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    public boolean addDepartment(DepartmentDTO departmentDTO) {
        try {
            Department department = new Department();
            department.setName(departmentDTO.getName());
            departmentRepository.save(department);
            if (departmentDTO.getManager() != null && departmentDTO.getManager().getId() != null) {
                employeeRepository.updateManagedDepartment(departmentDTO.getManager().getId(), department.getId());
                Employee manager = employeeRepository.findById(departmentDTO.getManager().getId())
                        .orElseThrow(() -> new EmployeeNotFoundException(departmentDTO.getManager().getId()));
                manager.addRole("ROLE_MANAGER");
                employeeRepository.save(manager);
            }
            departmentDTO.getTeams().forEach(team -> {
                teamRepository.updateDepartment(team.getId(), department.getId());
                employeeRepository.findAll().forEach(employee -> {
                    employeeRepository.changeDepartmentRegardingToTeam(department.getId(), team.getId());
                });
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modifyDepartment(DepartmentDTO updatedDepartment, UUID id) {
        try {
            Department outdatedDepartment = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
            departmentRepository.updateName(updatedDepartment.getId(), updatedDepartment.getName());
            if (outdatedDepartment.getManager() != null && outdatedDepartment.getManager().getId() != null) {
                employeeRepository.updateManagedDepartment(outdatedDepartment.getManager().getId(), null);
            }
            if (updatedDepartment.getManager() != null && updatedDepartment.getManager().getId() != null) {
                employeeRepository.updateManagedDepartment(updatedDepartment.getManager().getId(), updatedDepartment.getId());
                Employee manager = employeeRepository.findById(updatedDepartment.getManager().getId())
                        .orElseThrow(() -> new EmployeeNotFoundException(updatedDepartment.getManager().getId()));
                manager.addRole("ROLE_MANAGER");
                employeeRepository.save(manager);
            }
            outdatedDepartment.getTeams().forEach((team) -> {
                teamRepository.updateDepartment(team.getId(), null);
                employeeRepository.findAll().forEach(employee -> {
                    employeeRepository.changeDepartmentRegardingToTeam(null, team.getId());
                });
            });
            updatedDepartment.getTeams().forEach((team) -> {
                teamRepository.updateDepartment(team.getId(), updatedDepartment.getId());
                employeeRepository.findAll().forEach(employee -> {
                    employeeRepository.changeDepartmentRegardingToTeam(updatedDepartment.getId(), team.getId());
                });
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean deleteDepartment(UUID id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) return false;
        Employee manager = department.get().getManager();
        if(manager != null) {
            manager.setManagedDepartment(null);
            employeeRepository.save(manager);
        }
        department.get().getTeams().forEach(team -> {
            team.setDepartment(null);
            teamRepository.save(team);
        });
        department.get().getEmployees().forEach(employee -> {
            employee.setDepartment(null);
            employeeRepository.save(employee);
        });
        departmentRepository.delete(department.get());
        return true;
    }
}
