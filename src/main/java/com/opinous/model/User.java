package com.opinous.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.opinous.model.common.BaseEntity;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Where(clause = "is_active=1")
public class User extends BaseEntity {

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

	public User() {

	}

	public User(Long id, String email, String username, String password, String confirmPassword, String firstName,
			String lastName, Set<Role> roles) {
		setId(id);
		this.email = email;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
	}
	public User getCopy() {
		return new User(getId(), email, username, password, confirmPassword, firstName, lastName, roles);
	}
}
