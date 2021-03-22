package com.thanglv.hibernate.services;

import com.thanglv.hibernate.dto.DepartmentDTO;
import com.thanglv.hibernate.dto.EmployeeDTO;
import com.thanglv.hibernate.entity.Department;
import com.thanglv.hibernate.entity.Employee;
import com.thanglv.hibernate.repository.DepartmentRepository;
import com.thanglv.hibernate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Employee createEmployee() {
        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentId(1L);
        if (optionalDepartment.isPresent()) {
            Employee employee = Employee.builder()
                    .fullName("myTest")
                    .department(optionalDepartment.get())
                    .build();
            return employeeRepository.save(employee);
        } else
            return null;
    }

    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().filter(Objects::nonNull).map(this::employeeToEmployeeDTO).collect(Collectors.toList());
    }

    public EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        if (employee == null)
            return null;
        else {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeId(employee.getEmployeeId());
            employeeDTO.setFullName(employee.getFullName());
            employeeDTO.setDepartment(new DepartmentDTO(employee.getDepartment()));
            return employeeDTO;
        }
    }
}
