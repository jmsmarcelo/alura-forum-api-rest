package com.github.jmsmarcelo.alura.forum.api.domain.topic.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jmsmarcelo.alura.forum.api.base.exceptions.Errors;
import com.github.jmsmarcelo.alura.forum.api.base.exceptions.ValidatorException;
import com.github.jmsmarcelo.alura.forum.api.domain.course.CourseRepository;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataRecord;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicRepository;

import jakarta.validation.Valid;

@Component
public class ValidatorTopicRecordCheck implements ValidatorTopicRecord {
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public void validate(@Valid TopicDataRecord data) {
		if(topicRepository.existsByTitleAndActiveTrue(data.title()))
			throw new ValidatorException(Errors.DUPLICATE, "Title already exists!");
		if(topicRepository.existsByMessageAndActiveTrue(data.message()))
			throw new ValidatorException(Errors.DUPLICATE, "Message already exists!");
		if(!courseRepository.existsByIdAndActiveTrue(data.courseId()))
			throw new ValidatorException(Errors.NOT_FOUND, "Course ID does not exist!");
	}
}
	
