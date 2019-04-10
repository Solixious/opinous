package com.opinous.service;

import com.opinous.enums.NotificationType;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface NotificationService {

	/**
	 * @param model The web's Model object
	 * @param type The type of notification
	 * @param message The notification text to be shown in the UI
	 */
	void sendUINotification(Model model, NotificationType type, String message);

	void saveNotification();
}
