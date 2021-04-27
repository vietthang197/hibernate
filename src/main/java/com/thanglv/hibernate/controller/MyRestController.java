package com.thanglv.hibernate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thanglv.hibernate.controller.vm.LoginVM;
import com.thanglv.hibernate.dto.EmployeeDTO;
import com.thanglv.hibernate.entity.*;
import com.thanglv.hibernate.filter.JWTFilter;
import com.thanglv.hibernate.filter.TokenProvider;
import com.thanglv.hibernate.repository.*;
import com.thanglv.hibernate.services.DepartmentService;
import com.thanglv.hibernate.services.EmployeeService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/")
@EnableAsync
public class MyRestController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private ApiGroupRepository apiGroupRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/department")
    public Department getDepartment() {
       return departmentService.createDepartment();
    }

//    @GetMapping("/test-gen-employee")
//    @Async
//    public String status() {
//        List<Employee> employees = new ArrayList<>();
//        Optional<Department> department = departmentRepository.findByDepartmentId(1L);
//        for (int i = 0; i < 1000000000; i++) {
//            employees.add(new Employee("test", department.get()));
//            if (i % 10000 == 0) {
//                employeeRepository.saveAll(employees);
//                employees.clear();
//            }
//        }
//        return "OK";
//    }

    @PostMapping("/employee")
    public Employee createEmployee() {
        return employeeService.createEmployee();
    }

    @PostAuthorize("hasAuthority('ROLE_FUCK')")
    @GetMapping("/employee")
    public Page<Employee> getEmployees(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return employeeRepository.findAll(pageable);
    }


    @GetMapping("/department")
    public Page<Department> getDepartments() {
        Pageable pageable = PageRequest.of(0, 100, Sort.by("departmentId"));
        return departmentRepository.findAll(pageable);
    }

//    @GetMapping("/init-data")
//    public User initData() {
//        Operation operation = new Operation("Employee", "read", new Date());
//        Operation operation1 = new Operation("Employee", "list", new Date());
//        Operation operation2 = new Operation("Employee", "edit", new Date());
//        Operation operation3 = new Operation("Employee", "delete", new Date());
//        operation = operationRepository.save(operation);
//        operation1 = operationRepository.save(operation1);
//        operation2 = operationRepository.save(operation2);
//        operation3 = operationRepository.save(operation3);
//
//        Set<Operation> operations = new HashSet<>();
//        operations.add(operation);
//        operations.add(operation1);
//        operations.add(operation2);
//        operations.add(operation3);
//        Authority authority = new Authority("ADMIN");
//        authority.setOperations(operations);
//        authorityRepository.save(authority);
//
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("admin"));
//        user.getAuthorities().add(authority);
//        userRepository.save(user);
//        return user;
//    }

    @PostMapping("/api-group")
    public ApiGroup createApiGroup(@RequestBody @Valid ApiGroup apiGroup) {
        return apiGroupRepository.save(apiGroup);
    }

    /**
     * API authenticate
     * @param loginVM
     * @return
     * @throws SQLException
     */
    @PostMapping("/authenticate")
    public ResponseEntity<String> authorize(@Valid @RequestBody LoginVM loginVM) throws SQLException, JsonProcessingException, UnsupportedEncodingException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(jwt,
                httpHeaders, HttpStatus.OK);
    }
}
