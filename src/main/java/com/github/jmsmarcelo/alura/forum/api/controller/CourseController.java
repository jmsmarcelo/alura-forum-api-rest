package com.github.jmsmarcelo.alura.forum.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<CourseDataDetail> add(@RequestBody @Valid CourseDataRecord data) {
		return ResponseEntity.ok(courseData.add(data));
	}
	@GetMapping
	public ResponseEntity<Page<CourseDataDetail>> pageAll(
			@PageableDefault(size=10, sort={"category"})Pageable pageable) {
		var page = repository.findAll(pageable).map(CourseDataDetail::new);
		return ResponseEntity.ok(page);
	}
	@GetMapping("/{category}")
	public ResponseEntity<Page<CourseDataDetail>> listByCategory(
			@PageableDefault(size=10, sort={"name"})@PathVariable String category, Pageable pageable) {
		var page = repository.findByCategory(category, pageable);
		if(page.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(page);
	}
	@GetMapping("/categories")
	public ResponseEntity<Page<List<String>>> listCategories(@PageableDefault(size=10, sort={"name"})Pageable pageable) {
		var page = repository.findAllSelectCategory(pageable);
		return ResponseEntity.ok(page);
	}
}
