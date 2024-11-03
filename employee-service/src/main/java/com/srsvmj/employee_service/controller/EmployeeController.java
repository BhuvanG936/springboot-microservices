package com.srsvmj.employee_service.controller;

import com.srsvmj.employee_service.dto.EmployeeDTO;
import com.srsvmj.employee_service.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    //1. Build Create New Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){

        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //2. Build Get Employee By ID REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){

        EmployeeDTO employee = employeeService.getEmployeeById(id);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    //3. Build Get All Employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){

        List<EmployeeDTO> employees = employeeService.getAllEmployees();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //4. Build Update Employee REST API
    @PostMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){

        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    //5. Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){

        employeeService.deleteEmployee(id);

        return new ResponseEntity<>("Employee Deleted Successfully!", HttpStatus.NOT_FOUND);
    }
}