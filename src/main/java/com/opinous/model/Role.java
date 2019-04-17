package com.opinous.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.opinous.model.common.BaseEntity;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Role extends BaseEntity {

	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
}
