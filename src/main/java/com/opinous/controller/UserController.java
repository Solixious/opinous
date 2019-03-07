package com.opinous.controller;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.URLMapping;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.service.RoomService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import com.opinous.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping(value = URLMapping.USER_REGISTRATION)
	public String registration(Model model) {
		model.addAttribute(AttributeName.USER_FORM, new User());
		return JSPMapping.REGISTRATION;
	}

	@PostMapping(value = URLMapping.USER_REGISTRATION)
	public String registration(@ModelAttribute(AttributeName.USER_FORM) User userForm, BindingResult bindingResult,
			Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return JSPMapping.REGISTRATION;
		}

		userService.saveUser(userForm);

		securityService.autologin(userForm.getUsername(), userForm.getPassword());

		return JSPMapping.HOME;
	}

	@GetMapping(value = URLMapping.USER_LOGIN)
	public String login(Model model, String error, String logout) {

		if (error != null) {
			model.addAttribute("error", "Your username and password is invalid");
		}

		if (logout != null) {
			model.addAttribute("message", "You have been logged out successfully");
		}

		return JSPMapping.LOGIN;
	}

	@GetMapping(value = URLMapping.USER_HOME)
	public String welcome(Model model) {
		Page<Room> rooms = roomService.getRooms(0);
		model.addAttribute(AttributeName.ROOMS, rooms.getContent());
		model.addAttribute(AttributeName.PAGE_NUMBER, 0);
		model.addAttribute(AttributeName.MAX_PAGE_NUMBER, rooms.getTotalPages());
		return JSPMapping.HOME;
	}

	@GetMapping(value = URLMapping.USER_HOME_PAGINATED)
	public String homePaginated(@PathVariable String page, Model model) {
		try {
			int pageNo = Integer.parseInt(page) - 1;
			Page<Room> rooms = roomService.getRooms(pageNo);
			model.addAttribute(AttributeName.ROOMS, rooms.getContent());
			model.addAttribute(AttributeName.PAGE_NUMBER, pageNo);
			model.addAttribute(AttributeName.MAX_PAGE_NUMBER, rooms.getTotalPages());
		} catch(NumberFormatException e) {
		}
		return JSPMapping.HOME;
	}
}
