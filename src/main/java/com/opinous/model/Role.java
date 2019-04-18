package com.opinous.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.opinous.model.common.BaseEntity;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_active=1")
public class Role extends BaseEntity {

	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
}
