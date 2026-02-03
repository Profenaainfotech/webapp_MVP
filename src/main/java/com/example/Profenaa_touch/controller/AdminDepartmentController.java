package com.example.Profenaa_touch.controller;

import com.example.Profenaa_touch.Repository.DepartmentRepository;
import com.example.Profenaa_touch.entity.Department;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/department")
public class AdminDepartmentController {

    private final DepartmentRepository departmentRepository;

    public AdminDepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }
}
