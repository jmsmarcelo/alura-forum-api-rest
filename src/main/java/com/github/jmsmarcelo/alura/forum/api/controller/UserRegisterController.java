package com.github.jmsmarcelo.alura.forum.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jmsmarcelo.alura.forum.api.domain.user.User;
import com.github.jmsmarcelo.alura.forum.api.domain.user.UserDataRecord;
import com.github.jmsmarcelo.alura.forum.api.domain.user.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("register")
public class UserRegisterController {
	@Autowired
	private UserRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> register(@RequestBody @Valid UserDataRecord data) {
		var user = new User(data);
		repository.save(user);
		return ResponseEntity.ok().build();
	}
}
