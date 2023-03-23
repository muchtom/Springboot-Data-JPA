package com.tatenda.practiseproject.repository;

import com.tatenda.practiseproject.entity.Course;
import com.tatenda.practiseproject.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

   Optional<Teacher> findTeacherByTeacherEmail(String email);

}
