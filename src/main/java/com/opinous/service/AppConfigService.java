package com.opinous.service;

import org.springframework.stereotype.Service;

@Service
public interface AppConfigService {

	String getAppConfig(String key, String defaultValue);
}
