package com.opinous.controller.admin;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.URLMapping;
import com.opinous.enums.NotificationType;
import com.opinous.enums.RoleConst;
import com.opinous.model.User;
import com.opinous.service.NotificationService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import com.opinous.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(URLMapping.ADMIN)
public class AdminModeratorController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private NotificationService notificationService;

	@GetMapping(value = URLMapping.NEW_MODERATOR)
	public String newModerator(Model model) {
		if (securityService.isAdmin()) {
			model.addAttribute(AttributeName.USER_FORM, new User());
			return JSPMapping.ADMIN_NEW_MODERATOR;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@PostMapping(value = URLMapping.NEW_MODERATOR)
	public String newModerator(@ModelAttribute(AttributeName.USER_FORM) User userForm, BindingResult bindingResult,
			Model model) {
		if (securityService.isAdmin()) {

			userValidator.validate(userForm, bindingResult);

			if (bindingResult.hasErrors()) {
				return JSPMapping.ADMIN_NEW_MODERATOR;
			}
			final RoleConst[] roles = new RoleConst[] { RoleConst.USER_ROLE, RoleConst.MODERATOR_ROLE };
			userService.saveUser(userForm, roles);
			model.addAttribute(AttributeName.USER_FORM, new User());
			notificationService.notify(model, NotificationType.success, "Created new moderator successfully!");
			return JSPMapping.ADMIN_NEW_MODERATOR;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@GetMapping(value = URLMapping.LIST_MODERATOR)
	public String listModerator(Model model) {
		if (securityService.isAdmin()) {
			final List<String> roles = new ArrayList<>();
			roles.add(RoleConst.MODERATOR_ROLE.toString());
			model.addAttribute(AttributeName.USER_LIST, userService.findBySpecificRoles(roles));
			return JSPMapping.ADMIN_LIST_MODERATOR;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}
}
