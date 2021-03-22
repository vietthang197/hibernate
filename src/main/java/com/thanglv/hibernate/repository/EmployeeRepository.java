package com.thanglv.hibernate.repository;

import com.thanglv.hibernate.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @EntityGraph(attributePaths = "department")
    Optional<Employee> findByEmployeeId(Long employeeId);

    @EntityGraph(attributePaths = "department")
    List<Employee> findAll();

    Page<Employee> findAllByFullNameAndEmployeeId(Pageable pageable, String fullName, Long employeeId);
}
