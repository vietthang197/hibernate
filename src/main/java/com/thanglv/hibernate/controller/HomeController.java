package com.thanglv.hibernate.controller;

import com.thanglv.hibernate.dto.EmployeeDTO;
import com.thanglv.hibernate.entity.Department;
import com.thanglv.hibernate.entity.Employee;
import com.thanglv.hibernate.repository.DepartmentRepository;
import com.thanglv.hibernate.repository.EmployeeRepository;
import com.thanglv.hibernate.services.DepartmentService;
import com.thanglv.hibernate.services.EmployeeService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@EnableAsync
public class HomeController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/department")
    public Department getDepartment() {
       return departmentService.createDepartment();
    }

    @GetMapping("/test-gen-employee")
    @Async
    public String status() {
        List<Employee> employees = new ArrayList<>();
        Optional<Department> department = departmentRepository.findByDepartmentId(1L);
        for (int i = 0; i < 1000000000; i++) {
            employees.add(new Employee("test", department.get()));
            if (i % 1000 == 0) {
                employeeRepository.saveAll(employees);
                employees.clear();
            }
        }
        return "OK";
    }

    @PostMapping("/employee")
    public Employee createEmployee() {
        return employeeService.createEmployee();
    }

    @GetMapping("/employee")
    public Page<Employee> getEmployees() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("employeeId"));
        return employeeRepository.findAllByFullNameAndEmployeeId(pageable, "test", 2002L);
    }

    @GetMapping("/department")
    public Page<Department> getDepartments() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("departmentId"));
        return departmentRepository.findAll(pageable);
    }
}
