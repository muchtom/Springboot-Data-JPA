package com.tatenda.practiseproject.api;

import com.tatenda.practiseproject.entity.Student;
import com.tatenda.practiseproject.DataTransferObject.StudentRequest;
import com.tatenda.practiseproject.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    private StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAllStudents")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/addNewStudent")
    public void addNewStudent(@RequestBody StudentRequest student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "/deleteStudent/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "/updateStudent/{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

    @GetMapping("/searchStudentByName")
    public ResponseEntity<List<Student>> searchStudentByName(@RequestParam("studentName") String studentName) {
        return ResponseEntity.ok(studentService.searchStudentByName(studentName));
    }

    @GetMapping("/searchStudentByNameOrEmail")
    public ResponseEntity<List<Student>> searchStudentByNameOrEmail(@RequestParam("studentNameorEmail") String studentNameOrEmail) {

        return ResponseEntity.ok(studentService.findStudentByStudentNameOrEmail(studentNameOrEmail));
    }

    @GetMapping("/searchStudentByNameAndEmail")
    public ResponseEntity<List<Student>> searchStudentByNameAndEmail
            (@RequestParam("studentName,studentEmail") String studentName, String studentEmail) {
        return ResponseEntity.ok(studentService.findStudentByNameAndEmail(studentName, studentEmail));
    }
}
