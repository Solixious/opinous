package com.opinous.service.impl;

import com.opinous.enums.NotificationType;
import com.opinous.repository.NotificationRepository;
import com.opinous.service.NotificationService;
import com.opinous.utils.PreCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public void sendUINotification(Model model, final NotificationType type, final String message) {
		PreCondition.checkNotNull(model, "model");
		PreCondition.checkNotNull(type, "type");
		PreCondition.checkNotNull(message, "message");
		model.addAttribute("notif", message);
		model.addAttribute("notifType", type.toString());
	}

	@Override
	public void saveNotification() {

	}
}
