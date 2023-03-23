package com.tatenda.practiseproject.service;

import com.tatenda.practiseproject.DataTransferObject.CourseMaterialRequest;
import com.tatenda.practiseproject.entity.Course;
import com.tatenda.practiseproject.entity.CourseMaterial;
import com.tatenda.practiseproject.exceptions.ExceptionMessage;
import com.tatenda.practiseproject.repository.CourseMaterialRepository;
import com.tatenda.practiseproject.repository.CourseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseMaterialService {

    private final CourseMaterialRepository courseMaterialRepository;

    private final CourseRepository courseRepository;

    public CourseMaterialService(CourseMaterialRepository courseMaterialRepository, CourseRepository courseRepository) {
        this.courseMaterialRepository = courseMaterialRepository;
        this.courseRepository = courseRepository;
    }

    public void addCourseMaterial(CourseMaterialRequest course) {
        Optional<CourseMaterial> courseByCourseMaterialUrl =
                courseMaterialRepository.findCourseMaterialByCourseMaterialUrl
                        (course.getCourseMaterialUrl());
        if (courseByCourseMaterialUrl.isPresent()) {
            throw new ExceptionMessage("Course Material already exits");
        }
        Optional<Course> findCourseById = courseRepository.
                findCourseByCourseId(course.getCourseId());
        Optional<CourseMaterial> findCourseByCourseId =
                courseMaterialRepository.findCourseMaterialByCourseCourseId(course.getCourseId());
        if (findCourseById.isPresent() && !findCourseByCourseId.isPresent()) {
            Course course1 = Course.builder()
                    .courseId(course.getCourseId())
                    .build();
            CourseMaterial courseMaterial = CourseMaterial.builder()
                    .courseMaterialUrl(course.getCourseMaterialUrl())
                    .course(course1)
                    .build();
            courseMaterialRepository.save(courseMaterial);

        }
     if(!findCourseById.isPresent()) {
         throw new ExceptionMessage("Course with id " + course.getCourseId() + " is not found");

     }
     if(findCourseByCourseId.isPresent()){
         throw new ExceptionMessage("Course with the id " + course.getCourseId() + " is present");
     }

    }

    @Transactional
    public void updateCourseMaterial(Long courseMaterialId,String courseMaterialUrl){
        CourseMaterial courseMaterial = courseMaterialRepository.findById(courseMaterialId).
                orElseThrow(() -> new ExceptionMessage("Course Material with the id "+ courseMaterialId + " is not present"));
        Optional<CourseMaterial> courseByCourseMaterialUrl =
                courseMaterialRepository.findCourseMaterialByCourseMaterialUrl
                        (courseMaterialUrl);
        if(courseByCourseMaterialUrl.isPresent()){
            throw new ExceptionMessage("Course Material is already present");
        }
        if(courseMaterialUrl !=null && courseMaterialUrl.length() > 0 &&
                !Objects.equals(courseMaterial.getCourseMaterialUrl(), courseMaterialUrl)
        ){
            courseMaterial.setCourseMaterialUrl(courseMaterialUrl);
        }

    }
    public void deleteCourseMaterial(Long courseMaterialId){
        boolean courseMaterialExists = courseMaterialRepository.existsById(courseMaterialId);
        if(!courseMaterialExists){
            throw new ExceptionMessage("Course material with id "+ courseMaterialId + " does not exists");
        }
        courseMaterialRepository.deleteById(courseMaterialId);
    }

   public List<CourseMaterial> getAllCourseMaterial(){
        return  courseMaterialRepository.findAll();
   }
}
