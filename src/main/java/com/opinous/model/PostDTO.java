package com.opinous.model;

import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDTO {
	private Long id;

	private Alias alias;

	private String text;

	private Date createDate;

	private Date updateDate;

	private String updatedTimeAgo;
	
	private Map<String, Long> reactionCounts;
	
	private Map<String, Long> reactions;
}
