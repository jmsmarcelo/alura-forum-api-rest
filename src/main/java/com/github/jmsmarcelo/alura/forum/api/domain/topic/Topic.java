package com.github.jmsmarcelo.alura.forum.api.domain.topic;

import java.time.LocalDateTime;
import java.util.List;

import com.github.jmsmarcelo.alura.forum.api.domain.course.Course;
import com.github.jmsmarcelo.alura.forum.api.domain.response.Response;
import com.github.jmsmarcelo.alura.forum.api.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="topics")
@Entity(name="Topic")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Topic {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private TopicStatus status = TopicStatus.UNANSWERED;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="course_id")
	private Course course;
	@OneToMany
	private List<Response> resposes;
	private Boolean active;
	
	public Topic(TopicDataRecord data, User author, Course course) {
		this.title = data.title();
		this.message = data.message();
		this.author = author;
		this.course = course;
		this.active = true;
	}
	public void update(TopicDataUpdate data) {
		if(data.title() != null && !data.title().isBlank())
			this.title = data.title();
		if(data.message() != null && !data.message().isBlank())
			this.message = data.message();
	}
	public void disable() {
		this.active = false;
	}
	public void unsolved() {
		this.status = TopicStatus.UNSOLVED;
	}
}
