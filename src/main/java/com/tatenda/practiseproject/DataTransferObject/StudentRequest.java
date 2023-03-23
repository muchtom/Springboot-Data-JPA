package com.tatenda.practiseproject.DataTransferObject;

import com.tatenda.practiseproject.entity.Guardian;
import lombok.Data;

import javax.persistence.Embedded;
import java.time.LocalDate;

@Data
public class StudentRequest {
    private String name;
    private String email;
    private LocalDate dob;

    @Embedded
    private Guardian guardian;
}
