package com.srsvmj.department_service.service;

import com.srsvmj.department_service.dto.DepartmentDTO;
import com.srsvmj.department_service.entity.Department;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Long id);

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO updateDepartment(Long id, Department department);

    void deleteDepartment(Long id);

    DepartmentDTO getDepartmentByCode(String departmentCode);
}
