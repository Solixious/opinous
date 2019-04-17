package com.opinous.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.opinous.model.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
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
