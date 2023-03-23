package com.tatenda.practiseproject.util;

import com.tatenda.practiseproject.entity.Course;
import com.tatenda.practiseproject.service.dto.CourseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoUtil {
    public static CourseDto convertToDto(Course course){
        CourseDto courseDto = new CourseDto();
        BeanUtils.copyProperties(course, courseDto);
        return courseDto;
    }
}
