package com.github.jmsmarcelo.alura.forum.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jmsmarcelo.alura.forum.api.domain.response.ResponseData;
import com.github.jmsmarcelo.alura.forum.api.domain.response.ResponseDataDetail;
import com.github.jmsmarcelo.alura.forum.api.domain.response.ResponseDataRecord;
import com.github.jmsmarcelo.alura.forum.api.domain.response.ResponseDataUpdate;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("responses")
@SecurityRequirement(name="bearer-key")
public class ResponseController {
	@Autowired
	private ResponseData responseData;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ResponseDataDetail> add(
			@RequestBody @Valid ResponseDataRecord data) {
		return ResponseEntity.ok(responseData.add(data));
	}
	@GetMapping("/topic")
	public ResponseEntity<?> listByTopic(
			@RequestBody @Valid ResponseDataUpdate data, Pageable pageable){
		return ResponseEntity.ok(responseData.byTopic(data, pageable));
	}
}
