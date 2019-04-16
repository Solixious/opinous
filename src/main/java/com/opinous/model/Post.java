package com.opinous.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.opinous.model.common.CommonProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Post extends CommonProperties {

	@ManyToOne
	@JoinColumn(name = "aliasId")
	private Alias alias;

	@Lob
	private String text;
}
