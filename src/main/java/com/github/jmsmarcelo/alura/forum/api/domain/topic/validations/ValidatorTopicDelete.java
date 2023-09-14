package com.github.jmsmarcelo.alura.forum.api.domain.topic.validations;

import org.springframework.stereotype.Component;

import com.github.jmsmarcelo.alura.forum.api.base.exceptions.Errors;
import com.github.jmsmarcelo.alura.forum.api.base.exceptions.ValidatorException;
import com.github.jmsmarcelo.alura.forum.api.domain.Data;

@Component
public class ValidatorTopicDelete extends Data {
	public void validate(Long id) {
		var topic = topicRepository.getByIdAndActiveTrue(id);
		
		if(topic == null)
			throw new ValidatorException(Errors.NOT_FOUND,
					"Topic does not exist or has already been deleted!");
		if(!topic.getAuthor().equals(userLogged.get()))
			throw new ValidatorException(Errors.NO_PERMISSION,
					"You do not have permission to delete this topic!");
			
	}
}
