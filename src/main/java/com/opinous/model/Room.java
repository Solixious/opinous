package com.opinous.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.opinous.model.common.CommonProperties;

@Entity
@Getter
@Setter
@ToString
public class Room extends CommonProperties {

	@ManyToOne
	@JoinColumn(name = "creatorId")
	private Alias creator;

	@Lob
	private String title;

	@Lob
	private String description;
}
