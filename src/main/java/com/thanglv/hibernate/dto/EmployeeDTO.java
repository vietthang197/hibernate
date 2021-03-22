package com.thanglv.hibernate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thanglv.hibernate.entity.Employee;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO {

    private Long employeeId;

    private String fullName;

    private DepartmentDTO department;

    public EmployeeDTO() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }
}
