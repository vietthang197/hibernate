package com.thanglv.hibernate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thanglv.hibernate.entity.Department;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDTO implements Serializable {

    private Long departmentId;

    private String name;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Department department) {
        this.departmentId = department.getDepartmentId();
        this.name = department.getName();
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
