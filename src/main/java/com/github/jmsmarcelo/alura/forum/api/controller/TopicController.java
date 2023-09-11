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
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataDetail;
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
	public ResponseEntity<TopicDataDetail> add(
			@RequestBody @Valid TopicDataRecord data) {
		var td = topicData.add(data);
		return ResponseEntity.ok(new TopicDataDetail(td));
	}
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicDataDetail> update(
			@RequestBody @Valid TopicDataUpdate data, @PathVariable Long id) {
		return ResponseEntity.ok(topicData.update(data, id));
	}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> disable(@PathVariable Long id) {
		var topic = repository.findByIdAndActiveTrue(id);
		topic.disable();
		return ResponseEntity.ok("Topic removed");
	}
	@GetMapping
	public ResponseEntity<Page<TopicDataDetail>> listAll(
			@PageableDefault(size=10, sort={"creationDate"}) Pageable pageable) {
		var page = repository.findAllAndActiveTrue(pageable);
		return ResponseEntity.ok(page);
	}
	@GetMapping("/course/id/{courseId}")
	public ResponseEntity<Page<TopicDataDetail>> listByCourse(
			@PageableDefault(size=10, sort={"creationDate"})
			@PathVariable Long courseId, Pageable pageable) {
		var page = repository.findByCourseId(courseId, pageable);
		return ResponseEntity.ok(page);
	}
	@GetMapping("/year/{year}")
	public ResponseEntity<Page<TopicDataDetail>> listByYear(
			@PageableDefault(size=10, sort={"creationDate"})
			@PathVariable Long year, Pageable pageable) {
		var page = repository.findByYearAndActiveTrue(year, pageable);
		return ResponseEntity.ok(page);
	}
	@GetMapping("/{id}")
	public ResponseEntity<TopicDataDetail> getById(
			@PageableDefault(size=10, sort={"creationDate"})
			@PathVariable Long id) {
		var topic = repository.getReferenceById(id);
		return ResponseEntity.ok(new TopicDataDetail(topic));
	}
}
