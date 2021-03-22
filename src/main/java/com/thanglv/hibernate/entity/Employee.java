package com.thanglv.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EMPLOYEE_SEQ")
    @SequenceGenerator(name = "EMPLOYEE_SEQ", sequenceName = "SEQUENCE_EMPLOYEE", allocationSize = 10000, initialValue = 1)
    private Long employeeId;

    private String fullName;

    @ManyToOne
    @JsonIgnoreProperties(value = "employees", allowSetters = true)
    private Department department;

    public Employee() {
    }

    public Employee(Long employeeId, String fullName, Department department) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.department = department;
    }

    public Employee department(Department department) {
        this.department = department;
        return this;
    }

    public Employee(String fullName, Department department) {
        this.fullName = fullName;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
