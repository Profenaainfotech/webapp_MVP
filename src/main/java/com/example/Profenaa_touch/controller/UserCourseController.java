package com.example.Profenaa_touch.controller;

import com.example.Profenaa_touch.Repository.*;
import com.example.Profenaa_touch.entity.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserCourseController {

    private final EnrollmentRepository enrollRepo;
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;
    private final TopicRepository topicRepo;

    public UserCourseController(
            EnrollmentRepository e,
            CourseRepository c,
            UserRepository u,
            TopicRepository t
    ) {
        enrollRepo = e;
        courseRepo = c;
        userRepo = u;
        topicRepo = t;
    }

    @PostMapping("/enroll/{courseId}")
    public void enroll(@PathVariable Long courseId, Authentication auth) {

        User user = userRepo.findByEmail(auth.getName()).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();

        if (!enrollRepo.existsByUserAndCourse(user, course)) {
            Enrollment e = new Enrollment();
            e.setUser(user);
            e.setCourse(course);
            enrollRepo.save(e);
        }
    }

    @GetMapping("/course/{courseId}/topics")
    public List<Topic> topics(@PathVariable Long courseId, Authentication auth) {

        User user = userRepo.findByEmail(auth.getName()).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();

        if (!enrollRepo.existsByUserAndCourse(user, course))
            throw new RuntimeException("Buy course first");

        return topicRepo.findByCourseId(courseId);
    }
    @GetMapping("/courses")
    public List<Course> getAllPublishedCourses() {
        return courseRepo.findByPublishedTrue();
    }

}
