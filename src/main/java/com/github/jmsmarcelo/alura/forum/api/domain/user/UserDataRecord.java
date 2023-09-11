package com.github.jmsmarcelo.alura.forum.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDataRecord(
		@NotBlank @Pattern(regexp="[a-zA-Z0-9]{5,20}")
		String login,
		@NotBlank @Email
		String email,
		@NotBlank
		String pass) {

}
