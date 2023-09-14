package com.github.jmsmarcelo.alura.forum.api.domain.response;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.jmsmarcelo.alura.forum.api.domain.Data;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicStatus;

@Service
public class ResponseData extends Data {
	
	public ResponseDataDetail add(ResponseDataRecord data) {
		var response = new Response(data,
				userRepository.findByLoginAndActiveTrue(userLogged.get().getLogin()),
				topicRepository.getReferenceById(data.topicId()));
		responseRepository.save(response);
		
		if(response.getTopic().getStatus().equals(TopicStatus.UNANSWERED))
			response.getTopic().unsolved();
		
		return new ResponseDataDetail(response);
	}
	public Object byTopic(Long id, Pageable pageable) {
		return responseRepository.findAllByTopicId(id, pageable);
	}
}
