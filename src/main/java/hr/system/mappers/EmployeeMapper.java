package hr.system.mappers;

import hr.system.dtos.EmployeeDTO;
import hr.system.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    @Autowired
    private final IdAndNameMapper idAndNameMapper;

    public EmployeeMapper(IdAndNameMapper idAndNameMapper) {
        this.idAndNameMapper = idAndNameMapper;
    }

    public EmployeeDTO convertToDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        if (employee == null) return employeeDTO;
        employeeDTO.setId(employee.getId());
        employeeDTO.setUsername(employee.getUsername());
        employeeDTO.setName(employee.getName());
        employeeDTO.setBirthDate(employee.getBirthDate());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setGraduationDate(employee.getGraduationDate());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setExpertises(employee.getExpertises());
        employeeDTO.setDepartment(idAndNameMapper.convertToDto(employee.getDepartment()));
        employeeDTO.setTeam(idAndNameMapper.convertToDto(employee.getTeam()));
        employeeDTO.setManager(idAndNameMapper.convertToDto(employee.getManager()));
        employeeDTO.setManagedDepartment(idAndNameMapper.convertToDto(employee.getManagedDepartment()));
        employeeDTO.setManagedTeam(idAndNameMapper.convertToDto(employee.getManagedTeam()));
        employeeDTO.setManagedEmployees(employee.getManagedEmployees().stream().map(idAndNameMapper::convertToDto).toList());
        return employeeDTO;
    }
}
