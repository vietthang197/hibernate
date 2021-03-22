package com.thanglv.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "department") // OneToMany da co san lazy fetching
    @BatchSize(size = 20)
    private List<Employee> employees;

    public Department() {
    }

    public Department(Long departmentId, String name, List<Employee> employees) {
        this.departmentId = departmentId;
        this.name = name;
        this.employees = employees;
    }

    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
