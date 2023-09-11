package com.github.jmsmarcelo.alura.forum.api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByLoginAndActiveTrue(String login);

	UserDetails findByLogin(String login);

}