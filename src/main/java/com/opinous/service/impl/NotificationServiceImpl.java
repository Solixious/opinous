package com.opinous.service.impl;

import com.opinous.enums.NotificationType;
import com.opinous.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

	@Override
	public void notify(Model model, final NotificationType type, final String message) {
		if(model == null || type == null || message == null) {
			log.error("Cannot notify for a null value. model: {}, type: {}, message: {}", model, type,
				message);
			return;
		}
		model.addAttribute("notif", message);
		model.addAttribute("notifType", type.toString());
	}
}
