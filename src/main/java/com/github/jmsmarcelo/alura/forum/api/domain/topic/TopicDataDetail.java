package com.github.jmsmarcelo.alura.forum.api.domain.topic;

import java.time.LocalDateTime;

public record TopicDataDetail(
		Long id, String title, String message, LocalDateTime creationDate,
		TopicStatus status, Long authorId, Long courseId) {
	public TopicDataDetail(Topic topic) {
		this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(),
				topic.getStatus(), topic.getAuthor().getId(), topic.getCourse().getId());
	}
}
