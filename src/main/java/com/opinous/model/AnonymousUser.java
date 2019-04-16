package com.opinous.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

import com.opinous.model.common.CommonProperties;

@Entity
@Getter
@Setter
@ToString
public class AnonymousUser extends CommonProperties {

	private String name;
	
	private String displayPicture;
}
