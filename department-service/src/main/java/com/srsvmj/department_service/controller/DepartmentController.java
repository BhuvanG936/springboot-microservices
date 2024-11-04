package com.srsvmj.department_service.controller;

import com.srsvmj.department_service.dto.DepartmentDTO;
import com.srsvmj.department_service.entity.Department;
import com.srsvmj.department_service.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    //1. POST, Build Save Department REST API, http://localhost:8080//api/departments
    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO){

        DepartmentDTO savedDept = departmentService.saveDepartment(departmentDTO);

        return new ResponseEntity<>(savedDept, HttpStatus.CREATED);
    }

    //2. Build Get department REST API
    @GetMapping("/deptcode/{department-code}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("department-code") String departmentCode){

        DepartmentDTO departmentDTO = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    //3. GET, Build Get Department By ID, REST API, http://localhost:8080//api/departments/2
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id){

        DepartmentDTO department = departmentService.getDepartmentById(id);

        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    //4. GET, Build Get All Department, REST API, http://localhost:8080//api/departments
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){

        List<DepartmentDTO> departments = departmentService.getAllDepartments();

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    //5. PUT, Build Update Department REST API
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody Department department){

        DepartmentDTO updatedDepartment = departmentService.updateDepartment(id, department);

        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    //6. DELETE, Build Delete Department REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){

        departmentService.deleteDepartment(id);

        return new ResponseEntity<>("Department Deleted Successfully!", HttpStatus.NOT_FOUND);
    }
}
