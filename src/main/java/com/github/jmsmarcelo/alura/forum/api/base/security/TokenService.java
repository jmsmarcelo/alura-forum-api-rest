package com.github.jmsmarcelo.alura.forum.api.base.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.jmsmarcelo.alura.forum.api.domain.user.User;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("AluraForum.api")
					.withSubject(user.getUsername())
					.withExpiresAt(expirationDate())
					.sign(algorithm);
		} catch(JWTCreationException ex) {
			throw new RuntimeException("erro ao gerar token JWT", ex);
		}
	}
	public String getSubject(String tokenJWT) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer("AluraForum.api").build()
					.verify(tokenJWT).getSubject();
		} catch(JWTVerificationException ex) {
			throw new RuntimeException("Token JWT inválido ou expirado!");
		}
	}

	private Instant expirationDate() {
		return LocalDateTime.now().plusDays(8).toInstant(
				OffsetDateTime.now().getOffset());
	}
}
