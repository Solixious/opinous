package com.opinous.repository;

import com.opinous.model.AppConfiguration;
import com.opinous.repository.common.CustomPagingAndSortRepository;

public interface AppConfigRepository extends CustomPagingAndSortRepository<AppConfiguration, Long> {
	AppConfiguration findByKey(String key);
}
