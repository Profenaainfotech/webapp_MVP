package com.example.Profenaa_touch.Repository;

import com.example.Profenaa_touch.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {

    boolean existsByUserAndCourse(User user, Course course);

    List<Enrollment> findByUser(User user);
}
