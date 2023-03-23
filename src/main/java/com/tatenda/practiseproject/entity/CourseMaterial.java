package com.tatenda.practiseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="courseMaterial",
        uniqueConstraints = @UniqueConstraint(
                name= "courseMaterialUrl_unique",
                columnNames = "courseMaterialUrl"
        )
  )
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseMaterialId;
    @Column(nullable = false)
    private String courseMaterialUrl;

   @OneToOne
    private Course course;
}
