package com.github.jmsmarcelo.alura.forum.api.domain.topic.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jmsmarcelo.alura.forum.api.base.exceptions.Errors;
import com.github.jmsmarcelo.alura.forum.api.base.exceptions.ValidatorException;
import com.github.jmsmarcelo.alura.forum.api.domain.course.CourseRepository;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataUpdate;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicRepository;
import com.github.jmsmarcelo.alura.forum.api.domain.user.UserLogged;

import jakarta.validation.Valid;

@Component
public class ValidatorTopicUpdateCheck implements ValidatorTopicUpdate {
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserLogged userLogged;

	@Override
	public void validate(@Valid TopicDataUpdate data) {
		var topic = topicRepository.getByIdAndActiveTrue(data.id());
		var course = courseRepository.getByIdAndActiveTrue(data.courseId());
		var titleExists = topicRepository.existsByTitleAndActiveTrue(data.title());
		var msgExists = topicRepository.existsByMessageAndActiveTrue(data.message());

		if(topic == null)
			throw new ValidatorException(Errors.NOT_FOUND, "Topic ID does not exist or has been deleted!");
		if(!topic.getAuthor().equals(userLogged.get()))
			throw new ValidatorException(Errors.NO_PERMISSION, "You do not have permission to edit this topic!");
		if(titleExists && !topic.getTitle().equals(data.title()))
			throw new ValidatorException(Errors.DUPLICATE, "Title already exists!");
		if(msgExists && !topic.getMessage().equals(data.message()))
			throw new ValidatorException(Errors.DUPLICATE, "Message already exists!");
		if(course == null && data.courseId() != null)
			throw new ValidatorException(Errors.NOT_FOUND, "Course ID does not exist or has been deleted!");
	}
}
