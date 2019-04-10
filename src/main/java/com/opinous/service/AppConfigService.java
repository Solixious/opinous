package com.opinous.service;

import org.springframework.stereotype.Service;

@Service
public interface AppConfigService {

	/**
	 * @param key The key of the app configuration
	 * @param defaultValue The default value of the app configuration
	 * @return The value of the app configuration to be used
	 */
	String getAppConfig(String key, String defaultValue);
}
