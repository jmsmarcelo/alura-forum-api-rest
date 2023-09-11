package com.github.jmsmarcelo.alura.forum.api.domain.category;

public record CategoryDataDetail(Long id, String name) {
	public CategoryDataDetail(Category category) {
		this(category.getId(), category.getName());
	}
}
