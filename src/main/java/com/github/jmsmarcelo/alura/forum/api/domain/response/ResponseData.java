package com.github.jmsmarcelo.alura.forum.api.domain.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicRepository;
import com.github.jmsmarcelo.alura.forum.api.domain.user.UserRepository;

@Service
public class ResponseData {
	@Autowired
	private ResponseRepository responseRepository;
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private UserRepository userRepository;
	
	public ResponseDataDetail add(ResponseDataRecord data) {
		var response = new Response(data, userRepository.findByLoginAndActiveTrue(
				SecurityContextHolder.getContext().getAuthentication().getName()),
				topicRepository.getReferenceById(data.topicId()));
		responseRepository.save(response);
		return new ResponseDataDetail(response);
	}
}
