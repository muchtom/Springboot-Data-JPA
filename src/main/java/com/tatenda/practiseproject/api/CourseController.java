package com.tatenda.practiseproject.api;

import com.tatenda.practiseproject.DataTransferObject.CourseRequest;
import com.tatenda.practiseproject.entity.Course;
import com.tatenda.practiseproject.service.CourseService;
import com.tatenda.practiseproject.service.dto.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping("/getAllCourses")
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return ResponseEntity.status(HttpStatus.FOUND)
                        .body(courseService.getAllCourses());
    }
    @PostMapping("/createCourse")
    public void AddNewCourse(@RequestBody CourseRequest courseRequest){
        courseService.AddNewCourse(courseRequest);
    }

    @PutMapping(path="/updateCourse/{courseId}")
    public void updateCourse(@PathVariable("courseId") Long courseId,
                             @RequestParam(required = false) String courseName,
                             @RequestParam(required = false) String courseCredit){
        courseService.updateCourse(courseId,courseName,courseCredit);
    }
    @DeleteMapping(path="/deleteCourse/{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId){
        courseService.deleteCourse(courseId);
    }


    @GetMapping("/getSortedList/{field}")
    public List<Course> getCourseDataByFieldName(@PathVariable String field){
        List<Course> allProducts = courseService.getSortedListByField(field);
        return allProducts;
    }


    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Course> getCoursesWithPagination(@PathVariable int offset, @PathVariable int pageSize){
        Page<Course> findCourseWithPagination = courseService.getCoursesWithPagination(offset, pageSize);
        return findCourseWithPagination;
    }

}
