package com.tatenda.practiseproject.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_db",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "email_address"
)
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "email_address",
            nullable = false
    )
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;

    @Embedded
    private  Guardian guardian;
    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

}
