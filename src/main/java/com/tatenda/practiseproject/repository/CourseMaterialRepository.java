package com.tatenda.practiseproject.repository;

import com.tatenda.practiseproject.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {

    Optional<CourseMaterial> findCourseMaterialByCourseMaterialUrl(String courseMaterialUrl);
    Optional<CourseMaterial> findCourseMaterialByCourseCourseId(Long courseId);

}
