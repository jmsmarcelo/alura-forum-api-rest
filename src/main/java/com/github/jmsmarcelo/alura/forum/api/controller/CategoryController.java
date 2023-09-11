package com.github.jmsmarcelo.alura.forum.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jmsmarcelo.alura.forum.api.domain.category.Category;
import com.github.jmsmarcelo.alura.forum.api.domain.category.CategoryData;
import com.github.jmsmarcelo.alura.forum.api.domain.category.CategoryDataDetail;
import com.github.jmsmarcelo.alura.forum.api.domain.category.CategoryRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("categories")
@SecurityRequirement(name="bearer-key")
public class CategoryController {
	@Autowired
	private CategoryRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoryDataDetail> add(@RequestBody @Valid CategoryData data) {
		var category = new Category(data);
		repository.save(category);
		return ResponseEntity.ok(new CategoryDataDetail(category));
	}
	@GetMapping
	public ResponseEntity<Page<CategoryDataDetail>> pageAll(
			@PageableDefault(size=10, sort={"name"})Pageable pageable) {
		var page = repository.findAll(pageable).map(CategoryDataDetail::new);
		return ResponseEntity.ok(page);
	}
}
