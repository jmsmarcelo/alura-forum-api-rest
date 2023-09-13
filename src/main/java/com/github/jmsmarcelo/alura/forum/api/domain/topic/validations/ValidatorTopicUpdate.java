package com.github.jmsmarcelo.alura.forum.api.domain.topic.validations;

import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataUpdate;

import jakarta.validation.Valid;

public interface ValidatorTopicUpdate {
	public void validate(@Valid TopicDataUpdate data);
}
