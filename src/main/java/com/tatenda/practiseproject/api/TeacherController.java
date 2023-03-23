package com.tatenda.practiseproject.api;

import com.tatenda.practiseproject.DataTransferObject.TeacherDTO;
import com.tatenda.practiseproject.entity.Teacher;
import com.tatenda.practiseproject.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @PostMapping("/addNewTeacher")
    public void  addNewTeacher(@RequestBody TeacherDTO teacher){
         teacherService.AddNewTeacher(teacher);
    }
    @GetMapping("/getTeachers")
    public List<Teacher> getAllTeachers(){
        return  teacherService.getAllTeachers();
    }
}
