package com.github.jmsmarcelo.alura.forum.api.domain.response.validations;

import org.springframework.stereotype.Component;

import com.github.jmsmarcelo.alura.forum.api.base.exceptions.Errors;
import com.github.jmsmarcelo.alura.forum.api.base.exceptions.ValidatorException;
import com.github.jmsmarcelo.alura.forum.api.domain.Data;
import com.github.jmsmarcelo.alura.forum.api.domain.response.ResponseDataUpdate;

@Component
public class ValidatorResponseList extends Data {
	public void validate(ResponseDataUpdate data) {
		if(!topicRepository.existsByIdAndActiveTrue(data.topicId()))
			throw new ValidatorException(Errors.NOT_FOUND,
					"Topic ID does not exist or has been deleted!");
	}
}
