package com.github.jmsmarcelo.alura.forum.api.domain.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jmsmarcelo.alura.forum.api.domain.course.CourseRepository;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.validations.ValidatorTopicRecord;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.validations.ValidatorTopicUpdate;
import com.github.jmsmarcelo.alura.forum.api.domain.user.UserLogged;

@Service
public class TopicData {
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private List<ValidatorTopicRecord> recordValidators;
	@Autowired
	private List<ValidatorTopicUpdate> updateValidators;
	@Autowired
	private UserLogged userLogged;
	
	public TopicDataDetail add(TopicDataRecord data) {
		recordValidators.forEach(v -> v.validate(data));
		var topic = new Topic(data, userLogged.get(),
				courseRepository.getReferenceById(data.courseId()));
		topicRepository.save(topic);
		return new TopicDataDetail(topic);
	}
	public TopicDataDetail update(TopicDataUpdate data) {
		updateValidators.forEach(v -> v.validate(data));
		var topic = topicRepository.getByIdAndActiveTrue(data.id());
		topic.update(data);
		return new TopicDataDetail(topic);
	}
	
}
