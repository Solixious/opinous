package com.opinous.controller;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.URLMapping;
import com.opinous.model.User;
import com.opinous.repository.UserRepository;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping(URLMapping.PROFILE)
@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping(URLMapping.USER_PROFILE_BASIC)
    public String basic(Model model) {
    	User user = userService.findByUsername(securityService.findLoggedInUsername());
    	user.setPassword("");
        model.addAttribute(AttributeName.USER_DETAIL, user);
        return JSPMapping.USER_PROFILE_BASIC;
    }
    
    @PostMapping(URLMapping.USER_PROFILE_BASIC)
    public String basic(@ModelAttribute(AttributeName.USER_DETAIL) User updateUser,
			BindingResult bindingResult, Model model) {
    	if (securityService.isUser()) {
			if (bindingResult.hasErrors()) {
				return JSPMapping.USER_PROFILE_BASIC;
			}

			User user = userRepository.findById(updateUser.getId()).get();
			userService.copyNecessaryUpdates(updateUser, user);
			userService.updateUser(user);
			user.setPassword("");
	        model.addAttribute(AttributeName.USER_DETAIL, user);
			return JSPMapping.USER_PROFILE_BASIC;
		} else {
			log.error("Illegal attempt to update user details.");
			return JSPMapping.ERROR;
		}

    }
}
