package com.tatenda.practiseproject.service;

import com.tatenda.practiseproject.DataTransferObject.TeacherDTO;
import com.tatenda.practiseproject.entity.Course;
import com.tatenda.practiseproject.entity.Teacher;
import com.tatenda.practiseproject.exceptions.ExceptionMessage;
import com.tatenda.practiseproject.repository.CourseRepository;
import com.tatenda.practiseproject.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    Set<Course> courses = new HashSet<>();

    public void AddNewTeacher(TeacherDTO teacher) {
        Optional<Teacher> findTeacherByEmail = teacherRepository.findTeacherByTeacherEmail(teacher.getTeacherEmail());
        if (!findTeacherByEmail.isPresent()) {
            for (Long id : teacher.getCourses()) {
                Optional<Course> findCourseById = courseRepository.findCourseByCourseId(id);
                if (!findCourseById.isPresent()) throw new ExceptionMessage("Course not found");
                courses.add(findCourseById.get());
            }
            Teacher teacher1 = Teacher.builder()
                    .teacherFirstName(teacher.getTeacherFirstName())
                    .teacherLastName(teacher.getTeacherLastName())
                    .teacherEmail(teacher.getTeacherEmail())
                    .courses(courses)
                    .build();
            teacherRepository.save(teacher1);
        }
        if(findTeacherByEmail.isPresent()){
            throw new ExceptionMessage("Teacher already present");
        }
    }

    public List<Teacher> getAllTeachers(){
        return  teacherRepository.findAll();
    }
}
