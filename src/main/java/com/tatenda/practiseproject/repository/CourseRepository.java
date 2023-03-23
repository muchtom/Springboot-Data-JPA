package com.tatenda.practiseproject.repository;

import com.tatenda.practiseproject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Optional<Course> findCourseByCourseName(String courseName);
    Optional<Course> findCourseByCourseId(Long id);
}
