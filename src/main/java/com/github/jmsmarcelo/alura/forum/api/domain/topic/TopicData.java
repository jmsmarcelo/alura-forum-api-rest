package com.github.jmsmarcelo.alura.forum.api.domain.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jmsmarcelo.alura.forum.api.base.InfoData;
import com.github.jmsmarcelo.alura.forum.api.base.InfoData.DataDetail;
import com.github.jmsmarcelo.alura.forum.api.base.InfoData.Status;
import com.github.jmsmarcelo.alura.forum.api.domain.Data;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.validations.ValidatorTopicDelete;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.validations.ValidatorTopicRecord;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.validations.ValidatorTopicUpdate;

@Service
public class TopicData extends Data {
	@Autowired
	private ValidatorTopicRecord recordValidators;
	@Autowired
	private ValidatorTopicUpdate updateValidators;
	@Autowired
	private ValidatorTopicDelete deleteValidators;
	
	public TopicDataDetail add(TopicDataRecord data) {
		recordValidators.validate(data);
		var topic = new Topic(data, userLogged.get(),
				courseRepository.getReferenceById(data.courseId()));
		topicRepository.save(topic);
		return new TopicDataDetail(topic);
	}
	public TopicDataDetail update(TopicDataUpdate data) {
		updateValidators.validate(data);
		var topic = topicRepository.getByIdAndActiveTrue(data.id());
		topic.update(data);
		return new TopicDataDetail(topic);
	}
	public DataDetail delete(Long id) {
		deleteValidators.validate(id);
		var topic = topicRepository.getByIdAndActiveTrue(id);
		topic.disable();
		return InfoData.get(Status.DELETED , "This topic has been deleted!");
	}
}
