package com.opinous.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString
public class AnonymousUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String displayPicture;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayPicture() {
		return displayPicture;
	}

	public void setDisplayPicture(String displayPicture) {
		this.displayPicture = displayPicture;
	}
}
