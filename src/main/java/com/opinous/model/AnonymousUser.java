package com.opinous.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

import com.opinous.model.common.BaseEntity;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_active=1")
public class AnonymousUser extends BaseEntity {

	private String name;
	
	private String displayPicture;
}
