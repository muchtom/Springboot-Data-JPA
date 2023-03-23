package com.tatenda.practiseproject.DataTransferObject;

import lombok.Builder;
import lombok.Data;

@Data
public class CourseRequest {

    private String courseName;
    private String courseCredit;
}
