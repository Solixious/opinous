package com.opinous.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opinous.model.AppConfiguration;

public interface AppConfigRepository extends JpaRepository<AppConfiguration, Long>{
	AppConfiguration findByKey(String key);
}
