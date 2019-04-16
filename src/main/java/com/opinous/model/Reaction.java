package com.opinous.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.opinous.model.common.CommonProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Reaction extends CommonProperties {

	@ManyToOne
	@JoinColumn(name = "aliasId")
	private Alias alias;
	
	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;

	private String reactionType;
}
