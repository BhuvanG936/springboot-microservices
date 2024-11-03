package com.srsvmj.department_service.service.impl;

import com.srsvmj.department_service.dto.DepartmentDTO;
import com.srsvmj.department_service.entity.Department;
import com.srsvmj.department_service.exception.ResourceNotFoundException;
import com.srsvmj.department_service.mapper.DepartmentMapper;
import com.srsvmj.department_service.repository.DepartmentRepository;
import com.srsvmj.department_service.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {

        //Convert DepartmentDTO to Department JPA Entity
        Department department = new Department(
                departmentDTO.getId(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getDepartmentDescription(),
                departmentDTO.getDepartmentCode()
        );

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDTO savedDepartmentDTO = new DepartmentDTO(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );
        return savedDepartmentDTO;
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exists with id: "+id));

        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map(DepartmentMapper::mapToDepartmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, Department department) {

        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Department does not exist with id: "+id));

        existingDepartment.setId(department.getId());
        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setDepartmentDescription(department.getDepartmentDescription());
        existingDepartment.setDepartmentCode(department.getDepartmentCode());

        Department updatedDepartment = departmentRepository.save(existingDepartment);

        return modelMapper.map(updatedDepartment, DepartmentDTO.class);
    }

    @Override
    public void deleteDepartment(Long id) {

        if(departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        }
    }
}
