package com.opinous.service.impl;

import com.opinous.utils.PreCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.model.AppConfiguration;
import com.opinous.repository.AppConfigRepository;
import com.opinous.service.AppConfigService;

@Slf4j
@Service
public class AppConfigServiceImpl implements AppConfigService {

	@Autowired
	private AppConfigRepository appConfigRepository;
	
	@Override
	public String getAppConfig(final String key, final String defaultValue, final String description) {
		PreCondition.checkNotNull(key, "key");
		AppConfiguration appConfig = appConfigRepository.findByKey(key);
		if(appConfig == null) {
			appConfigRepository.save(new AppConfiguration(key, defaultValue, description));
			log.info("Added new app config with key: {}, value: {}", key, defaultValue);
			return defaultValue;
		}
		return appConfig.getValue();
	}
}
