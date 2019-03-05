package com.opinous.repository;

import com.opinous.model.AnonymousUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousUserRepository extends JpaRepository<AnonymousUser, Long> {
	public AnonymousUser findByName(String name);
}
