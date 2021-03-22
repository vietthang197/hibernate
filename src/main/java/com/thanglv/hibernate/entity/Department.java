package com.thanglv.hibernate.entity;

import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    private String name;

    @OneToMany(mappedBy = "department") // OneToMany da co san lazy fetching
    private Set<Employee> employees = new HashSet<>();

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    public Department() {
    }

    public Department(Long departmentId, String name, Set<Employee> employees, Location location) {
        this.departmentId = departmentId;
        this.name = name;
        this.employees = employees;
        this.location = location;
    }

    public Department(String name, Set<Employee> employees) {
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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Department employees(Set<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public Department addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setDepartment(this);
        return this;
    }

    public Department removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setDepartment(null);
        return this;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
