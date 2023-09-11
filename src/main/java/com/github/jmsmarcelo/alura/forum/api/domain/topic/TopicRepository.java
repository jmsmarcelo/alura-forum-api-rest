package com.github.jmsmarcelo.alura.forum.api.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Long> {
	
	Page<TopicDataDetail> findByCourseId(Long courseId, Pageable pageable);
	@Query("""
			SELECT t FROM Topic t
				WHERE YEAR(t.creationDate) = :year AND active=true
			""")
	Page<TopicDataDetail> findByYearAndActiveTrue(Long year, Pageable pageable);
	Boolean existsByTitleAndActiveTrue(String title);
	Boolean existsByTitleAndActiveFalse(String title);
	Boolean existsByMessageAndActiveTrue(String message);
	Boolean existsByMessageAndActiveFalse(String message);
	Topic findByIdAndActiveTrue(Long id);
	@Query("""
			SELECT t FROM Topic t
				WHERE t.active=true
			""")
	Page<TopicDataDetail> findAllAndActiveTrue(Pageable pageable);
}
