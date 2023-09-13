package com.github.jmsmarcelo.alura.forum.api.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jmsmarcelo.alura.forum.api.domain.category.CategoryRepository;

@Service
public class CourseData {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CourseRepository courseRepository;
	
	public CourseDataDetail add(CourseDataRecord data) {
		var category = categoryRepository.getReferenceById(data.categoryId());
		var course = new Course(null, data.name(), category, true);
		courseRepository.save(course);
		return new CourseDataDetail(course);
	}
}
