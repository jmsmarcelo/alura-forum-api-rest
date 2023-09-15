package com.github.jmsmarcelo.alura.forum.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jmsmarcelo.alura.forum.api.base.security.DataTokenJWT;
import com.github.jmsmarcelo.alura.forum.api.base.security.TokenService;
import com.github.jmsmarcelo.alura.forum.api.domain.user.User;
import com.github.jmsmarcelo.alura.forum.api.domain.user.UserDataAuthentication;

import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
public class UserLoginController {
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<DataTokenJWT> login(@RequestBody @Valid UserDataAuthentication data) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(
				data.login(), data.pass());
		
		var authentication = manager.authenticate(authenticationToken);
		System.out.println(authenticationToken);
		var tokenJWT = tokenService.generateToken((User)authentication.getPrincipal());
		return ResponseEntity.ok(new DataTokenJWT(tokenJWT));				
	}
}
