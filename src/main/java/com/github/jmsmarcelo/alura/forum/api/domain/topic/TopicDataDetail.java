package com.github.jmsmarcelo.alura.forum.api.domain.topic;

import java.time.LocalDateTime;

public record TopicDataDetail(
		String title, String message, LocalDateTime creationDate,
		TopicStatus status, Long authorId, Long courseId) {
	public TopicDataDetail(Topic topic) {
		this(topic.getTitle(), topic.getMessage(), topic.getCreationDate(),
				topic.getStatus(), topic.getAuthor().getId(), topic.getCourse().getId());
	}
}
