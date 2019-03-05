package com.opinous.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.model.AppConfiguration;
import com.opinous.repository.AppConfigRepository;
import com.opinous.service.AppConfigService;

@Service
public class AppConfigServiceImpl implements AppConfigService {

	@Autowired
	private AppConfigRepository appConfigRepository;
	
	@Override
	public String getAppConfig(String key, String defaultValue) {
		AppConfiguration appConfig = appConfigRepository.findByKey(key);
		if(appConfig == null) {
			appConfig = new AppConfiguration();
			appConfig.setKey(key);
			appConfig.setValue(defaultValue);
			appConfigRepository.save(appConfig);
			return defaultValue;
		}
		return appConfig.getValue();
	}

}
