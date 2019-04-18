package com.opinous.model.dto;

import java.util.Date;

import com.opinous.model.Alias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomDTO {
	private Long id;
	private Alias creator;
	private String title;
	private String description;
	private Integer postCount;
	private Integer participantCount;
	private String createdTimeAgo;
	private String updatedTimeAgo;
	private Date createDate;
	private Date updateDate;
}
