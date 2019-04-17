package com.opinous.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.opinous.model.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@ToString
@Where(clause = "is_active=1")
public class Alias extends BaseEntity {

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
