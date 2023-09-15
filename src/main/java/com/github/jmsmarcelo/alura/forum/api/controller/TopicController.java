package com.github.jmsmarcelo.alura.forum.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicData;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataRecord;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataUpdate;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("topics")
@SecurityRequirement(name="bearer-key")
public class TopicController {
	@Autowired
	private TopicRepository repository;
	@Autowired
	private TopicData topicData;

	@PostMapping
	@Transactional
	public ResponseEntity<?> add(
			@RequestBody @Valid TopicDataRecord data) {
		return ResponseEntity.ok(topicData.add(data));
	}
	@PutMapping
	@Transactional
	public ResponseEntity<?> update(
			@RequestBody @Valid TopicDataUpdate data) {
		return ResponseEntity.ok(topicData.update(data));
	}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return ResponseEntity.ok(topicData.delete(id));
	}
	@GetMapping
	public ResponseEntity<Page<?>> pageAll(
			@PageableDefault(size=10, sort={"creationDate"}) Pageable pageable) {
		var page = repository.findAllAndActiveTrue(pageable);
		return ResponseEntity.ok(page);
	}
	@GetMapping("/course-{id}")
	public ResponseEntity<Page<?>> pageByCourse(
			@PageableDefault(size=10, sort={"creationDate"})
			@PathVariable Long id, Pageable pageable) {
		var page = repository.findByCourseId(id, pageable);
		return ResponseEntity.ok(page);
	}
	@GetMapping("/year-{year}")
	public ResponseEntity<Page<?>> pageByYear(
			@PageableDefault(size=10, sort={"creationDate"})
			@PathVariable Long year, Pageable pageable) {
		var page = repository.findByYearAndActiveTrue(year, pageable);
		return ResponseEntity.ok(page);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return ResponseEntity.ok(repository.findByIdAndActiveTrue(id));
	}
}
