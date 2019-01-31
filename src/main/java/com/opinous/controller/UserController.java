package com.opinous.controller;

import com.opinous.constants.URLMappings;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.RoomRepository;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import com.opinous.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping(value = URLMappings.USER_REGISTRATION)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping(value = URLMappings.USER_REGISTRATION)
    public String registration(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if(bindingResult.hasErrors()) {
            return "/registration";
        }

        userService.saveUser(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPassword());

        return "/welcome";
    }

    @GetMapping(value = URLMappings.USER_LOGIN)
    public String login(Model model, String error, String logout) {

        if(error != null) {
            model.addAttribute("error", "Your username and password is invalid");
        }

        if(logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }

        return "login";
    }

    @GetMapping(value = URLMappings.USER_HOME)
    public String welcome(Model model) {
    	List<Room> rooms = new ArrayList<>();
    	rooms = roomRepository.findAll(Sort.by(Sort.Order.desc("id")));
    	model.addAttribute("rooms", rooms);
        return "welcome";
    }
}
