package com.example.Profenaa_touch.service;

import com.example.Profenaa_touch.Repository.CourseRepository;
import com.example.Profenaa_touch.Repository.DepartmentRepository;
import com.example.Profenaa_touch.entity.Course;
import com.example.Profenaa_touch.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepo;
    private final DepartmentRepository deptRepo;

    public CourseService(CourseRepository courseRepo,
                         DepartmentRepository deptRepo) {
        this.courseRepo = courseRepo;
        this.deptRepo = deptRepo;
    }

    // ADMIN → create course
    public Course createCourse(Long departmentId, String name, Double price) {

        Department dept = deptRepo.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Course course = new Course();
        course.setName(name);
        course.setPrice(price);
        course.setPublished(false); // hidden initially
        course.setDepartment(dept);

        return courseRepo.save(course);
    }

    // ADMIN → publish course
    public void publishCourse(Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setPublished(true);
        courseRepo.save(course);
    }

    // USER → view available courses
    public List<Course> getPublishedCourses() {
        return courseRepo.findByPublishedTrue();
    }
}
