package com.example.Profenaa_touch.service;

import com.example.Profenaa_touch.Repository.CourseRepository;
import com.example.Profenaa_touch.Repository.EnrollmentRepository;
import com.example.Profenaa_touch.Repository.UserRepository;
import com.example.Profenaa_touch.entity.Course;
import com.example.Profenaa_touch.entity.Enrollment;
import com.example.Profenaa_touch.entity.User;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final UserRepository userRepo;
    private final CourseRepository courseRepo;

    public EnrollmentService(EnrollmentRepository enrollmentRepo,
                             UserRepository userRepo,
                             CourseRepository courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    // USER → enroll (buy course)
    public void enroll(String email, Long courseId) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        boolean alreadyEnrolled =
                enrollmentRepo.existsByUserAndCourse(user, course);

        if (!alreadyEnrolled) {
            Enrollment enrollment = new Enrollment();
            enrollment.setUser(user);
            enrollment.setCourse(course);
            enrollmentRepo.save(enrollment);
        }
    }

    // CHECK ACCESS (important)
    public boolean hasAccess(String email, Long courseId) {

        User user = userRepo.findByEmail(email)
                .orElseThrow();

        Course course = courseRepo.findById(courseId)
                .orElseThrow();

        return enrollmentRepo.existsByUserAndCourse(user, course);
    }
}
