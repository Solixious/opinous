package com.opinous.service.impl;

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
	public String getAppConfig(final String key, final String defaultValue) {
		if(key == null) {
			log.error("Cannot retrieve app configuration for a null key value.");
			return null;
		}
		AppConfiguration appConfig = appConfigRepository.findByKey(key);
		if(appConfig == null) {

			appConfig = new AppConfiguration();
			appConfig.setKey(key);
			appConfig.setValue(defaultValue);

			appConfigRepository.save(appConfig);
			log.info("Added new app config with key: {}, value: {}", key, defaultValue);

			return defaultValue;
		}

		return appConfig.getValue();
	}
}
