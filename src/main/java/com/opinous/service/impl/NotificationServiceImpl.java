package com.opinous.service.impl;

import com.opinous.enums.NotificationType;
import com.opinous.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void notify(Model model, NotificationType type, String message) {
        model.addAttribute("notif", message);
        model.addAttribute("notifType", type.toString());
    }
}
