package com.github.jmsmarcelo.alura.forum.api.domain.topic.validations;

import org.springframework.stereotype.Component;

import com.github.jmsmarcelo.alura.forum.api.base.exceptions.Errors;
import com.github.jmsmarcelo.alura.forum.api.base.exceptions.ValidatorException;
import com.github.jmsmarcelo.alura.forum.api.domain.Data;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataRecord;

import jakarta.validation.Valid;

@Component
public class ValidatorTopicRecord extends Data {
	
	public void validate(@Valid TopicDataRecord data) {
		if(topicRepository.existsByTitleAndActiveTrue(data.title()))
			throw new ValidatorException(Errors.DUPLICATE, "Title already exists!");
		if(topicRepository.existsByMessageAndActiveTrue(data.message()))
			throw new ValidatorException(Errors.DUPLICATE, "Message already exists!");
		if(!courseRepository.existsByIdAndActiveTrue(data.courseId()))
			throw new ValidatorException(Errors.NOT_FOUND, "Course ID does not exist!");
	}
}
	
