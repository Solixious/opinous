package com.opinous.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.opinous.model.common.CommonProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Alias extends CommonProperties {

	@ManyToOne
	@JoinColumn(name = "roomId")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "anonymousUserId")
	private AnonymousUser anonymousUser;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public Alias() {

	}

	public Alias(final Room room, final AnonymousUser anonymousUser, final User user) {
		this.room = room;
		this.anonymousUser = anonymousUser;
		this.user = user;
	}
}
