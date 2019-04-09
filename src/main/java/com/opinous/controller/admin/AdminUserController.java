package com.opinous.controller.admin;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.Misc;
import com.opinous.constants.NavConstants;
import com.opinous.constants.URLMapping;
import com.opinous.enums.NotificationType;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(URLMapping.ADMIN)
public class AdminUserController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserService userService;

	@Autowired
	private NotificationService notificationService;

	@GetMapping(value = URLMapping.USER_HOME)
	public String adminHome(HttpServletRequest request, Model model) {
		if (securityService.isAdmin()) {
			log.debug("Going to admin control panel page.");
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			return JSPMapping.ADMIN_CONTROL_PANEL;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@GetMapping(value = URLMapping.NEW_USER)
	public String newUser(Model model) {
		if (securityService.isAdmin()) {
			model.addAttribute(AttributeName.USER_FORM, new User());
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			return JSPMapping.ADMIN_NEW_USER;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@PostMapping(value = URLMapping.NEW_USER)
	public String newUser(@ModelAttribute(AttributeName.USER_FORM) User userForm, BindingResult bindingResult,
			Model model) {
		if (securityService.isAdmin()) {
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			userValidator.validate(userForm, bindingResult);
			if (bindingResult.hasErrors()) {
				return JSPMapping.ADMIN_NEW_USER;
			}

			userService.saveUser(userForm);
			model.addAttribute(AttributeName.USER_FORM, new User());
			notificationService.sendUINotification(model, NotificationType.success, "Created new user successfully!");
			return JSPMapping.ADMIN_NEW_USER;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@GetMapping(value = URLMapping.UPDATE_USER)
	public String updateDeleteUser(Model model) {
		if (securityService.isAdmin()) {
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			return JSPMapping.ADMIN_UPDATE_DELETE_USER;
		}
		else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@GetMapping(value = URLMapping.UPDATE_USER + "/{username}")
	public String updateDeleteUser(@PathVariable("username") String username, Model model) {
		if (securityService.isAdmin()) {
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			User user = userService.findByUsername(username);

			if (user != null) {
				user = user.getCopy();
				user.setPassword("");
				user.setConfirmPassword("");
				model.addAttribute(AttributeName.USER_FORM, user);
				return JSPMapping.ADMIN_UPDATE_DELETE_USER;
			} else {
				return Misc.REDIRECT + URLMapping.ADMIN + URLMapping.LIST_USER + username;
			}
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@PostMapping(value = URLMapping.UPDATE_USER + "/{username}")
	public String updateDeleteUser(@ModelAttribute(AttributeName.USER_FORM) User updateUser,
			BindingResult bindingResult, Model model) {
		if (securityService.isAdmin()) {
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			if (bindingResult.hasErrors()) {
				return JSPMapping.ADMIN_UPDATE_DELETE_USER;
			}

			final User user = userService.findById(updateUser.getId());
			userService.copyNecessaryUpdates(updateUser, user);
			userService.updateUser(user);
			notificationService.sendUINotification(model, NotificationType.success, "User details updated successfully!");
			return JSPMapping.ADMIN_UPDATE_DELETE_USER;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@DeleteMapping(value = URLMapping.UPDATE_USER + "/{username}")
	public String deleteUser(@ModelAttribute(AttributeName.USER_FORM) User updateUser, BindingResult bindingResult,
			Model model) {
		if (securityService.isAdmin()) {
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			userService.delete(userService.findById(updateUser.getId()));
			notificationService.sendUINotification(model, NotificationType.success, "The user concerned deleted successfully!");
			return "redirect:" + URLMapping.ADMIN + URLMapping.LIST_USER;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@GetMapping(value = URLMapping.LIST_USER)
	public String listUsers(Model model) {
		if (securityService.isAdmin()) {
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			model.addAttribute(AttributeName.USER_LIST, userService.findAll());
			return JSPMapping.ADMIN_LIST_USER;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}

	@GetMapping(value = URLMapping.LIST_USER + "/{username}")
	public String listUsers(Model model, @PathVariable("username") String username) {
		if (securityService.isAdmin()) {
	        NavbarUtils.setNavPageActive(model, NavConstants.ADMIN);
			model.addAttribute(AttributeName.USER_LIST, userService.search(username));
			return JSPMapping.ADMIN_LIST_USER;
		} else {
			log.error("Illegal attempt to access admin page");
			return JSPMapping.ERROR;
		}
	}
}
