package com.opinous.controller;

import com.opinous.constants.AttributeName;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/profile")
@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/basic")
    public String basic(Model model) {
        model.addAttribute(AttributeName.USER_DETAIL,
            userService.findByUsername(securityService.findLoggedInUsername()));
        return "profile-basic";
    }
}
