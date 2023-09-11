package com.github.jmsmarcelo.alura.forum.api.domain.response;

import java.time.LocalDateTime;

import com.github.jmsmarcelo.alura.forum.api.domain.topic.Topic;
import com.github.jmsmarcelo.alura.forum.api.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="responses")
@Entity(name="Response")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Response {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String message;
	private LocalDateTime creationDate = LocalDateTime.now();
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="topic_id")
	private Topic topic;
	private Boolean solution = false;
	
	public Response(ResponseDataRecord data, User author, Topic topic) {
		this.message = data.message();
		this.author = author;
		this.topic = topic;
	}
}
