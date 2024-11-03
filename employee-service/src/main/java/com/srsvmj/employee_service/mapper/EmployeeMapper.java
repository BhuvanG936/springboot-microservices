package com.srsvmj.employee_service.mapper;

import com.srsvmj.employee_service.dto.EmployeeDTO;
import com.srsvmj.employee_service.entity.Employee;

public class EmployeeMapper {

    //Convert Employee to EmployeeDTO
    public static EmployeeDTO mapToEmployeeDTO(Employee employee){

        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    //Convert EmployeeDTO to Employee JPA Entity
    public static Employee mapToEmployee(EmployeeDTO employeeDTO){

        return new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail()
        );
    }
}
