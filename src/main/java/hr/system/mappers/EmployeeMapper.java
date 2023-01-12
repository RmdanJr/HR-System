package hr.system.mappers;

import hr.system.dtos.EmployeeDTO;
import hr.system.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    @Autowired
    private final EmployeeDepartmentMapper employeeDepartmentMapper;
    @Autowired
    private final EmployeeTeamMapper employeeTeamMapper;

    public EmployeeMapper(EmployeeDepartmentMapper employeeDepartmentMapper, EmployeeTeamMapper employeeTeamMapper) {
        this.employeeDepartmentMapper = employeeDepartmentMapper;
        this.employeeTeamMapper = employeeTeamMapper;
    }

    public EmployeeDTO convertToDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        if (employee == null) {
            return employeeDTO;
        }
        employeeDTO.setId(employee.getId());
        employeeDTO.setUsername(employee.getUsername());
        employeeDTO.setName(employee.getName());
        employeeDTO.setBirthDate(employee.getBirthDate());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setGraduationDate(employee.getGraduationDate());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setExpertises(employee.getExpertises());
        employeeDTO.setDepartment(employeeDepartmentMapper.convertToDto(employee.getDepartment()));
        employeeDTO.setTeam(employeeTeamMapper.convertToDto(employee.getTeam()));
        if (employee.getManager() != null) {
            employeeDTO.setManager(employee.getManager().getName());
        }
        employeeDTO.setManagedDepartment(employeeDepartmentMapper.convertToDto(employee.getManagedDepartment()));
        employeeDTO.setManagedTeam(employeeTeamMapper.convertToDto(employee.getManagedTeam()));
        employeeDTO.setManagedEmployees(employee.getManagedEmployees().stream().map(Employee::getName).toList());
        return employeeDTO;
    }
}
