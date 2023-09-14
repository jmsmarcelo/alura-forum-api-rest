package com.github.jmsmarcelo.alura.forum.api.domain.response;

import java.time.LocalDateTime;

public record ResponseDataDetail(
		Long id, String message, LocalDateTime creationDate, Long authorId, Long topicId, Boolean solution) {
	public ResponseDataDetail(Response response) {
		this(response.getId(), response.getMessage(), response.getCreationDate(),
		response.getAuthor().getId(), response.getTopic().getId(), response.getSolution());
	}
}
