package com.github.jmsmarcelo.alura.forum.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jmsmarcelo.alura.forum.api.domain.course.CourseData;
import com.github.jmsmarcelo.alura.forum.api.domain.course.CourseDataDetail;
import com.github.jmsmarcelo.alura.forum.api.domain.course.CourseDataRecord;
import com.github.jmsmarcelo.alura.forum.api.domain.course.CourseRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("courses")
@SecurityRequirement(name="bearer-key")
public class CourseController {
	@Autowired
	private CourseRepository repository;
	@Autowired
	private CourseData courseData;
	
	@PostMapping
	@Transactional
	@Secured(value={"ROLE_ADMIN"})
	public ResponseEntity<?> add(@RequestBody @Valid CourseDataRecord data) {
		return ResponseEntity.ok(courseData.add(data));
	}
	@GetMapping
	@Secured(value={"ROLE_ADMIN"})
	public ResponseEntity<?> pageAll(
			@PageableDefault(size=10, sort={"category"})Pageable pageable) {
		var page = repository.findAll(pageable).map(CourseDataDetail::new);
		return ResponseEntity.ok(page);
	}
	@GetMapping("/category-{id}")
	public ResponseEntity<?> listByCategory(
			@PageableDefault(size=10, sort={"name"})@PathVariable Long id, Pageable pageable) {
		var page = repository.findByCategoryIdAndActiveTrue(id, pageable);
		if(page.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(page);
	}
	@GetMapping("/categories")
	public ResponseEntity<?> listCategories(@PageableDefault(size=10, sort={"name"})Pageable pageable) {
		var page = repository.findAllSelectCategory(pageable);
		return ResponseEntity.ok(page);
	}
}
