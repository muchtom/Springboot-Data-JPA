package com.tatenda.practiseproject.service;

import com.tatenda.practiseproject.DataTransferObject.StudentRequest;
import com.tatenda.practiseproject.entity.Course;
import com.tatenda.practiseproject.entity.Guardian;
import com.tatenda.practiseproject.entity.Student;
import com.tatenda.practiseproject.exceptions.ExceptionMessage;
import com.tatenda.practiseproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

//    public List<Course> getSortedListByField(String field){
//        return courseRepository.findAll(Sort.by(Sort.Direction.ASC,field));
//    }


    public void addNewStudent(StudentRequest student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()) {
            throw new ExceptionMessage(("Email Taken"));
        }
        Guardian guardian = Guardian.builder()
                .name(student.getName())
                .email(student.getEmail())
                .build();

        Student student1 = Student.builder()
                .dob(student.getDob())
                .email(student.getEmail())
                .name(student.getName())
                .guardian(guardian)
                .build();

        studentRepository.save(student1);
    }


    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new ExceptionMessage("Student with id  "+ studentId + "  does not exits");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ExceptionMessage("Student with the id "+ studentId + " does not exists"));
        if(name != null && name.length() >0 &&
                !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(email != null && email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if(studentByEmail.isPresent()){
                throw new ExceptionMessage("email is taken");
            }
            student.setEmail(email);
        }

    }

    public List<Student> searchStudentByName(String studentName){
        List<Student> students = studentRepository.searchStudentsByName(studentName);
        return  students;
    }

    public List<Student> findStudentByStudentNameOrEmail(String studentNameOrEmail){
        List<Student> students = studentRepository.searchStudentByNameOrEmail(studentNameOrEmail);
        return students;
    }

    public List<Student> findStudentByNameAndEmail(String studentName, String studentEmail){
        List<Student> students = studentRepository.searchStudentByNameAndEmail(studentName,studentEmail);
                return students;
    }
}
