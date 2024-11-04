package com.srsvmj.employee_service.service.impl;

import com.srsvmj.employee_service.dto.APIResponseDTO;
import com.srsvmj.employee_service.dto.DepartmentDTO;
import com.srsvmj.employee_service.dto.EmployeeDTO;
import com.srsvmj.employee_service.entity.Employee;
import com.srsvmj.employee_service.exception.ResourceNotFoundException;
import com.srsvmj.employee_service.mapper.EmployeeMapper;
import com.srsvmj.employee_service.repository.EmployeeRepository;
import com.srsvmj.employee_service.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        Employee  employee = EmployeeMapper.mapToEmployee(employeeDTO);

        Employee savedEmployee =  employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public APIResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee","id",id));

       ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/deptcode/"+employee.getDepartmentCode(), DepartmentDTO.class);

       DepartmentDTO departmentDTO = responseEntity.getBody();

       EmployeeDTO employeeDTO = new EmployeeDTO(
               employee.getId(),
               employee.getFirstName(),
               employee.getLastName(),
               employee.getEmail(),
               employee.getDepartmentCode()
       );

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(employeeDTO);
        apiResponseDTO.setDepartment(departmentDTO);

        //return EmployeeMapper.mapToEmployeeDTO(employee);
        return apiResponseDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(EmployeeMapper::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployee) {

        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","id",id));

        employee.setId(updatedEmployee.getId());
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj =  employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee","id",id));

        employeeRepository.delete(employee);
    }
}