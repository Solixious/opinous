package com.opinous.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.opinous.model.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@ToString
@Where(clause = "is_active=1")
public class AppConfiguration extends BaseEntity {

	@Column(name = "confKey")
	private String key;

	@Column(name = "confValue")
	private String value;

	private String description;

	public AppConfiguration() {

	}

	public AppConfiguration(final String key, final String value) {
		this.key = key;
		this.value = value;
	}
}
