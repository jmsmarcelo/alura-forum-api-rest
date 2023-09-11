package com.github.jmsmarcelo.alura.forum.api.domain.topic.validations;

import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataRecord;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataUpdate;

import jakarta.validation.Valid;

public interface ValidatorTopic {
	public void validate(@Valid TopicDataRecord data);
	public void validate(TopicDataUpdate data);
}
