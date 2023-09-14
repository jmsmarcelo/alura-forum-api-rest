package com.github.jmsmarcelo.alura.forum.api.domain.course;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Page<CourseDataDetail> findByCategoryIdAndActiveTrue(Long categoryId, Pageable pageable);

	@Query("""
			SELECT c.category FROM Course c 
			""")
	List<String> findAllSelectCategory(Pageable pageable);

	boolean existsByIdAndActiveTrue(Long courseId);

	Course getByIdAndActiveTrue(Long courseId);

}
