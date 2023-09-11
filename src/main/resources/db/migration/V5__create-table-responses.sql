CREATE TABLE responses(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	message VARCHAR(5000) NOT NULL,
	creation_date DATETIME NOT NULL,
	author_id BIGINT NOT NULL,
	topic_id BIGINT NOT NULL,
	solution TINYINT DEFAULT 0,
	CONSTRAINT fk_responses_author_id FOREIGN KEY(author_id) REFERENCES users(id),
	CONSTRAINT fk_responses_topic_id FOREIGN KEY(topic_id) REFERENCES topics(id)
) ENGINE=InnoDB;