package com.github.jmsmarcelo.alura.forum.api.domain.topic.validations;

import org.springframework.stereotype.Component;

import com.github.jmsmarcelo.alura.forum.api.base.exceptions.Errors;
import com.github.jmsmarcelo.alura.forum.api.base.exceptions.ValidatorException;
import com.github.jmsmarcelo.alura.forum.api.domain.Data;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicDataUpdate;

import jakarta.validation.Valid;

@Component
public class ValidatorTopicUpdate extends Data {

	public void validate(@Valid TopicDataUpdate data) {
		var topic = topicRepository.getByIdAndActiveTrue(data.id());

		if(topic == null)
			throw new ValidatorException(Errors.NOT_FOUND,
					"Topic ID does not exist or has been deleted!");
		if(!topic.getAuthor().equals(userLogged.get()))
			throw new ValidatorException(Errors.NO_PERMISSION,
					"You do not have permission to edit this topic!");
		if(topicRepository.existsByTitleAndActiveTrue(data.title())
				&& !topic.getTitle().equals(data.title()))
			throw new ValidatorException(Errors.DUPLICATE, "Title already exists!");
		if(topicRepository.existsByMessageAndActiveTrue(data.message())
				&& !topic.getMessage().equals(data.message()))
			throw new ValidatorException(Errors.DUPLICATE, "Message already exists!");
		if(courseRepository.getByIdAndActiveTrue(data.courseId()) == null
				&& data.courseId() != null)
			throw new ValidatorException(Errors.NOT_FOUND,
					"Course ID does not exist or has been deleted!");
	}
}
