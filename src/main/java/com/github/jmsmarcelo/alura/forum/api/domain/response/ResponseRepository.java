package com.github.jmsmarcelo.alura.forum.api.domain.response;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {

	Page<ResponseDataDetail> findAllByTopicId(Long id, Pageable pageable);
}
