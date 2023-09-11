package com.github.jmsmarcelo.alura.forum.api.domain.topic.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jmsmarcelo.alura.forum.api.base.exceptions.ValidatorException;
import com.github.jmsmarcelo.alura.forum.api.domain.course.CourseRepository;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataRecord;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataUpdate;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicRepository;

import jakarta.validation.Valid;

@Component
public class ValidatorTopicTitleMessageUnique implements ValidatorTopic {
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public void validate(@Valid TopicDataRecord data) {
		exists(data.title(), data.message(), data.courseId());
	}
	@Override
	public void validate(TopicDataUpdate data) {
		exists(data.title(), data.message(), null);
	}
	
	private void exists(String title, String msg, Long courseId) {
		if(topicRepository.existsByTitleAndActiveTrue(title))
			throw new ValidatorException("Title already exists!");
		if(topicRepository.existsByMessageAndActiveTrue(msg))
			throw new ValidatorException("Message already exists!");
		if(courseId != null && !courseRepository.existsById(courseId))
			throw new ValidatorException("Course ID does not exist");
		
	}
}
	
