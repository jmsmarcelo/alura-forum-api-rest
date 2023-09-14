package com.github.jmsmarcelo.alura.forum.api.domain;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.jmsmarcelo.alura.forum.api.domain.course.CourseRepository;
import com.github.jmsmarcelo.alura.forum.api.domain.response.ResponseRepository;
import com.github.jmsmarcelo.alura.forum.api.domain.topic.TopicRepository;
import com.github.jmsmarcelo.alura.forum.api.domain.user.UserLogged;
import com.github.jmsmarcelo.alura.forum.api.domain.user.UserRepository;

public class Data {
	@Autowired
	protected TopicRepository topicRepository;
	@Autowired
	protected CourseRepository courseRepository;
	@Autowired
	protected UserRepository userRepository;
	@Autowired
	protected ResponseRepository responseRepository;
	@Autowired
	protected UserLogged userLogged;
}
