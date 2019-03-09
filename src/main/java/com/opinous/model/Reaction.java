package com.opinous.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Reaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "anonMapId")
	private AnonMap anonMap;
	
	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;

	private String reactionType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AnonMap getAnonMap() {
		return anonMap;
	}

	public void setAnonMap(AnonMap anonMap) {
		this.anonMap = anonMap;
	}
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	public String getReactionType() {
		return reactionType;
	}

	public void setReactionType(String reactionType) {
		this.reactionType = reactionType;
	}
}
