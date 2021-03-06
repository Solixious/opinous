package com.opinous.controller.admin;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.NavConstants;
import com.opinous.constants.URLMapping;
import com.opinous.enums.NotificationType;
import com.opinous.enums.RoleConst;
import com.opinous.model.User;
import com.opinous.service.NotificationService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import com.opinous.utils.NavbarUtils;
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
public class AdminAdminController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private NotificationService notificationService;

	@GetMapping(value = URLMapping.NEW_ADMIN)
	public String newAdmin(Model model) {
		if (securityService.isAdmin()) {
			model.addAttribute(AttributeName.USER_FORM, new User());
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			return JSPMapping.ADMIN_NEW_ADMIN;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@PostMapping(value = URLMapping.NEW_ADMIN)
	public String newAdmin(@ModelAttribute(AttributeName.USER_FORM) User userForm, BindingResult bindingResult,
			Model model) {
		if (securityService.isAdmin()) {

			userValidator.validate(userForm, bindingResult);

			if (bindingResult.hasErrors()) {
				return JSPMapping.ADMIN_NEW_ADMIN;
			}
			final RoleConst[] roles = new RoleConst[] { RoleConst.USER_ROLE, RoleConst.MODERATOR_ROLE, RoleConst.ADMIN_ROLE };
			userService.saveUser(userForm, roles);
			model.addAttribute(AttributeName.USER_FORM, new User());
			notificationService.sendUINotification(model, NotificationType.success, "New administrator created successfully!");
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			return JSPMapping.ADMIN_NEW_ADMIN;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@GetMapping(value = URLMapping.LIST_ADMINS)
	public String listAdmin(Model model) {
		if (securityService.isAdmin()) {
			final List<String> roles = new ArrayList<>();
			roles.add(RoleConst.ADMIN_ROLE.toString());
			model.addAttribute(AttributeName.USER_LIST, userService.findBySpecificRoles(roles));
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			return JSPMapping.ADMIN_LIST_ADMIN;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}
}
