package com.github.jmsmarcelo.alura.forum.api.domain.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.jmsmarcelo.alura.forum.api.domain.course.CourseRepository;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.validations.ValidatorTopic;
import com.github.jmsmarcelo.alura.forum.api.domain.user.UserRepository;

@Service
public class TopicData {
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private List<ValidatorTopic> validators;
	
	public Topic add(TopicDataRecord data) {
		validators.forEach(v -> v.validate(data));
		var topic = new Topic(data,
				userRepository.findByLoginAndActiveTrue(
						SecurityContextHolder.getContext().getAuthentication().getName()),
				courseRepository.getReferenceById(data.courseId()));
		topicRepository.save(topic);
		return topic;
	}
	public TopicDataDetail update(TopicDataUpdate data, @PathVariable Long id) {
		//validators.forEach(v -> v.validate(data));
		var topic = topicRepository.findByIdAndActiveTrue(id);
		topic.update(data);
		return new TopicDataDetail(topic);
	}
	
}
