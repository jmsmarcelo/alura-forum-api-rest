package com.github.jmsmarcelo.alura.forum.api.domain.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.jmsmarcelo.alura.forum.api.domain.Data;
import com.github.jmsmarcelo.alura.forum.api.domain.response.validations.ValidatorResponseList;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicStatus;

@Service
public class ResponseData extends Data {
	@Autowired
	private ValidatorResponseList listValidator;
	
	public ResponseDataDetail add(ResponseDataRecord data) {
		var response = new Response(data,
				userRepository.findByLoginAndActiveTrue(userLogged.get().getLogin()),
				topicRepository.getReferenceById(data.topicId()));
		responseRepository.save(response);
		if(response.getTopic().getStatus().equals(TopicStatus.UNANSWERED))
			response.getTopic().unsolved();
		return new ResponseDataDetail(response);
	}
	public ResponseDataDetail update(ResponseDataUpdate data) {
		return null;
		
	}
	public Page<?> byTopic(ResponseDataUpdate data, Pageable pageable) {
		listValidator.validate(data);
		return responseRepository.findAllByTopicId(data.topicId(), pageable);
	}
}
