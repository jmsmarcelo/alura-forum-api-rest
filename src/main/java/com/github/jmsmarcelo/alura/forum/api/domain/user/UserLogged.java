package com.github.jmsmarcelo.alura.forum.api.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserLogged {
	@Autowired
	UserRepository userRepository;
	
	public User get() {
		return userRepository.findByLoginAndActiveTrue(
				SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
