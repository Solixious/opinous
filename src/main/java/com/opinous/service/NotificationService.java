package com.opinous.service;

import com.opinous.enums.NotificationType;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface NotificationService {

	void sendUINotification(Model model, NotificationType type, String message);
}
