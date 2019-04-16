package com.opinous.model.common;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class CommonProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@CreationTimestamp
	private Date createDate;

	@UpdateTimestamp
	private Date updateDate;
}
