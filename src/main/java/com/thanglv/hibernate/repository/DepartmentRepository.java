package com.thanglv.hibernate.repository;

import com.thanglv.hibernate.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @EntityGraph(attributePaths = "employees")
    Optional<Department> findByDepartmentId(Long departmentId);

    @Query("FROM Department d JOIN FETCH d.employees")
    Page<Department> findAll(Pageable pageable);
}
