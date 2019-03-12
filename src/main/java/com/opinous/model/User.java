package com.opinous.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;

	private String username;

	private String password;

	@Transient
	private String confirmPassword;

	private String firstName;

	private String lastName;

	private String profilePicture;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@CreationTimestamp
	private Date createDate;

	@UpdateTimestamp
	private Date updateDate;

	public User() {

	}

	public User(Long id, String email, String username, String password, String confirmPassword, String firstName,
			String lastName, Set<Role> roles) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
	}
	public User getCopy() {
		return new User(id, email, username, password, confirmPassword, firstName, lastName, roles);
	}
}
