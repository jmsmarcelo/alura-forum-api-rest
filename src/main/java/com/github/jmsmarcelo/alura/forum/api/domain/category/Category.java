package com.github.jmsmarcelo.alura.forum.api.domain.category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="categories")
@Entity(name="Category")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	public Category(CategoryData data) {
		this.name = data.name();
	}
}
