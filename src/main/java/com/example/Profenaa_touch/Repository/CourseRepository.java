package com.example.Profenaa_touch.Repository;

import com.example.Profenaa_touch.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository
        extends JpaRepository<Course, Long> {
    List<Course> findByPublishedTrue();

}
