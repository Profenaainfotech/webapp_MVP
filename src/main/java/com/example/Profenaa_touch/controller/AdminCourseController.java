package com.example.Profenaa_touch.controller;

import com.example.Profenaa_touch.Repository.*;
import com.example.Profenaa_touch.entity.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/course")
public class AdminCourseController {

    private final CourseRepository courseRepo;
    private final DepartmentRepository deptRepo;

    public AdminCourseController(CourseRepository c, DepartmentRepository d) {
        courseRepo = c;
        deptRepo = d;
    }

    @PostMapping
    public Course create(@RequestParam Long departmentId,
                         @RequestParam String name,
                         @RequestParam Double price) {

        Department dept = deptRepo.findById(departmentId).orElseThrow();

        Course course = new Course();
        course.setName(name);
        course.setPrice(price);
        course.setPublished(false);
        course.setDepartment(dept);

        return courseRepo.save(course);
    }

    @PutMapping("/{id}/publish")
    public void publish(@PathVariable Long id) {
        Course course = courseRepo.findById(id).orElseThrow();
        course.setPublished(true);
        courseRepo.save(course);
    }
}
