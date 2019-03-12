package com.opinous.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class AnonMap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "roomId")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "anonymousUserId")
	private AnonymousUser anonymousUser;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@CreationTimestamp
	private Date createDate;

	@UpdateTimestamp
	private Date updateDate;
}
