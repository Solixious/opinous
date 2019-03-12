package com.opinous.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@ToString
public class AnonymousUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String displayPicture;

	@CreationTimestamp
	private Date createDate;

	@UpdateTimestamp
	private Date updateDate;
}
