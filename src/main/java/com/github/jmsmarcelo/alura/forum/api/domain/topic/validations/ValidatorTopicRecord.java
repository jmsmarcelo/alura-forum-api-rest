package com.github.jmsmarcelo.alura.forum.api.domain.topic.validations;

import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataRecord;

import jakarta.validation.Valid;

public interface ValidatorTopicRecord {
	public void validate(@Valid TopicDataRecord data);
}
