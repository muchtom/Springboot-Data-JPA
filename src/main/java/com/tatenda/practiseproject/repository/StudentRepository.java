package com.tatenda.practiseproject.repository;

import com.tatenda.practiseproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT student FROM Student student  WHERE " +
            " student.name LIKE CONCAT ('%',:studentName, '%')")
    List<Student> searchStudentsByName(String studentName);

    @Query("SELECT student FROM Student student WHERE"
            + " student.name LIKE CONCAT ('%',:studentNameOrEmail,'%')"
            + "Or  student.email LIKE CONCAT ('%',:studentNameOrEmail,'%') ")
    List<Student> searchStudentByNameOrEmail(String studentNameOrEmail);

    @Query("SELECT student FROM  Student student WHERE"
           + " student.name LIKE CONCAT ('%',:studentName,'%')"
           + "And student.email LIKE  CONCAT ('%',:studentEmail,'%')")
    List<Student> searchStudentByNameAndEmail(String studentName, String studentEmail);
}
