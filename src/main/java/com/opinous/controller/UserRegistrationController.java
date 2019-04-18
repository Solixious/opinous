package com.opinous.controller;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.NavConstants;
import com.opinous.constants.URLMapping;
import com.opinous.model.Room;
import com.opinous.model.dto.RoomDTO;
import com.opinous.model.User;
import com.opinous.service.RoomService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import com.opinous.utils.NavbarUtils;
import com.opinous.validator.UserValidator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
    private RoomService roomService;

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
		
		final Page<Room> rooms = roomService.getRooms(0);
		final List<RoomDTO> roomList = roomService.convertToRoomDTO(rooms.getContent());
		model.addAttribute(AttributeName.ROOMS, roomList);
        model.addAttribute(AttributeName.PAGE_NUMBER, 0);
        model.addAttribute(AttributeName.MAX_PAGE_NUMBER, rooms.getTotalPages());
        NavbarUtils.setNavPageActive(model, NavConstants.POPULAR);
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
}
