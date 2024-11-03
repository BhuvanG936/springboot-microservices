package com.srsvmj.department_service.mapper;


import com.srsvmj.department_service.dto.DepartmentDTO;
import com.srsvmj.department_service.entity.Department;

public class DepartmentMapper {

    //Convert Department to DepartmentDTO
    public static DepartmentDTO mapToDepartmentDTO(Department department){

        return new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
    }

    //Convert DepartmentDTO to Department JPA Entity
    public static Department mapToDepartment(DepartmentDTO departmentDTO){

        return new Department(
                departmentDTO.getId(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getDepartmentDescription(),
                departmentDTO.getDepartmentCode()
        );
    }
}

