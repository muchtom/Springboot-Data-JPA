package com.tatenda.practiseproject.service;

import com.tatenda.practiseproject.DataTransferObject.CourseRequest;
import com.tatenda.practiseproject.entity.Course;
import com.tatenda.practiseproject.exceptions.ExceptionMessage;
import com.tatenda.practiseproject.repository.CourseRepository;
import com.tatenda.practiseproject.service.dto.CourseDto;
import com.tatenda.practiseproject.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public void AddNewCourse(CourseRequest course){
        Optional<Course> courseByCourseName = courseRepository.findCourseByCourseName(course.getCourseName());
        if(courseByCourseName.isPresent()) {
            throw new ExceptionMessage(("Course Already Exists"));
        }
        Course course1 = Course.builder()
                .courseName(course.getCourseName())
                .courseCredit(course.getCourseCredit())
                .build();
        courseRepository.save(course1);
    }
    @Transactional
    public void updateCourse(Long courseId, String courseName, String courseCredit){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()->new ExceptionMessage("Course with the id " + courseId + " is not found"));
        if(courseName != null && courseName.length() > 0
                && !Objects.equals(course.getCourseName(), courseName)
        ){
            course.setCourseName(courseName);
        }
        if(courseCredit !=null && courseCredit.length() > 0 &&
                !Objects.equals(course.getCourseCredit(), courseCredit)
        ){
            course.setCourseCredit(courseCredit);
        }
    }

    public void deleteCourse(Long courseId){
        boolean courseExists = courseRepository.existsById(courseId);
        if(!courseExists){
            throw new  ExceptionMessage("Course with the id " +courseId + "does not exists");
        }
        courseRepository.deleteById(courseId);
    }

    public List<Course> getSortedListByField(String field){
        return courseRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public List<CourseDto> getAllCourses(){
        return courseRepository.findAll().stream()
                .map(course -> EntityDtoUtil.convertToDto(course))
                .collect(Collectors.toList());
    }

    public Page<Course> getCoursesWithPagination(int offset,int pageSize){
        Page<Course> courses = courseRepository.findAll(PageRequest.of(offset,pageSize));
        return  courses;
    }


}
