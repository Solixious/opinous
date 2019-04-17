package com.opinous.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

import com.opinous.model.common.BaseEntity;

@Entity
@Getter
@Setter
@ToString
public class AnonymousUser extends BaseEntity {

	private String name;
	
	private String displayPicture;
}
