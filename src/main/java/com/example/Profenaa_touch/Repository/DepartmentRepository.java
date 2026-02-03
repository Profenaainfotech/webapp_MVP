package com.example.Profenaa_touch.Repository;

import com.example.Profenaa_touch.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
}
