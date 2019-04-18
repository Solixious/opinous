package com.opinous.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.opinous.model.common.BaseEntity;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_active=1")
public class Room extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "creatorId")
	private Alias creator;

	@Lob
	private String title;

	@Lob
	private String description;
}
