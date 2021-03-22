package com.thanglv.hibernate.services;

import com.thanglv.hibernate.entity.Department;
import com.thanglv.hibernate.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment() {
        Department department = Department.builder()
                .name("Phong A")
                .build();
        return departmentRepository.save(department);
    }
}
