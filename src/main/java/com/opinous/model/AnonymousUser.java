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

	@ManyToMany
	@JoinTable(name = "anon_map", joinColumns = @JoinColumn(name = "anonymous_user_id"), 
		inverseJoinColumns = @JoinColumn(name = "room_id"))
	private Set<Room> rooms;
	
	@ManyToMany
	@JoinTable(name = "anon_map", joinColumns = @JoinColumn(name = "anonymous_user_id"), 
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;

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

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}public String getDisplayPicture() {
		return displayPicture;
	}

	public void setDisplayPicture(String displayPicture) {
		this.displayPicture = displayPicture;
	}
}
