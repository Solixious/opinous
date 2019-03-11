package com.opinous.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opinous.enums.ReactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDTO {
	private Long id;

	private AnonMap anonMap;

	private String text;

	private Date createDate;

	private Date updateDate;

	private Map<String, Long> reactionCounts;
	
	private Map<String, Long> reactions;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Map<String, Long> getReactionCounts() {
		return reactionCounts;
	}

	public void setReactionCounts(Map<String, Long> reactionCounts) {
		this.reactionCounts = reactionCounts;
	}

	public Map<String, Long> getReactions() {
		return reactions;
	}

	public void setLiked(Map<String, Long> reactions) {
		this.reactions = reactions;
	}
}
