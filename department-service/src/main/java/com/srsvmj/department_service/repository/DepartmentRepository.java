package com.srsvmj.department_service.repository;

import com.srsvmj.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
