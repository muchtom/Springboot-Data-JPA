package com.tatenda.practiseproject.DataTransferObject;

import com.tatenda.practiseproject.entity.Teacher;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
public class TeacherDTO {

    private String teacherFirstName;
    private  String teacherLastName;

    private String teacherEmail;
    @OneToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Long> courses=new HashSet<>();
}
