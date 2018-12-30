package com.opinous.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String description;
	
	@ManyToMany(mappedBy = "rooms")
	private Set<AnonymousUser> anonymousUsers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<AnonymousUser> getAnonymousUsers() {
		return anonymousUsers;
	}

	public void setAnonymousUsers(Set<AnonymousUser> anonymousUsers) {
		this.anonymousUsers = anonymousUsers;
	}
}
