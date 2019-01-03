package com.opinous.controller;


import com.opinous.enums.RoleConst;
import com.opinous.model.User;
import com.opinous.repository.UserRepository;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import com.opinous.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String adminHome(HttpServletRequest request) {
        if(securityService.hasRole(RoleConst.ADMIN_ROLE.toString()))
            return "admin-cp";
        else
            return "error";
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.GET)
    public String newUser(Model model) {
        if(securityService.hasRole(RoleConst.ADMIN_ROLE.toString())) {
            System.out.println("Loading reg page...");
            model.addAttribute("userForm", new User());
            return "/admin-new-user";
        }
        else
            return "error";
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.POST)
    public String newUser(@ModelAttribute("userForm") User userForm,
                          BindingResult bindingResult, Model model) {
        if(securityService.hasRole(RoleConst.ADMIN_ROLE.toString())) {

            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "admin-new-user";
            }

            userService.save(userForm);
            model.addAttribute("userForm", new User());
            return "/admin-new-user";
        }
        else
            return "error";
    }

    @RequestMapping(value = "/update-delete-user", method = RequestMethod.GET)
    public String updateDeleteUser(Model model) {
        return "admin-update-delete-user";
    }

    @RequestMapping(value = "/update-delete-user/{username}", method = RequestMethod.GET)
    public String updateDeleteUser(@PathVariable("username") String username, Model model) {
        User user = userRepository.findByUsername(username).getCopy();
        user.setPassword("");
        user.setConfirmPassword("");

        if(user != null) {
            model.addAttribute("userForm", user);
        }

        return "admin-update-delete-user";
    }

    @RequestMapping(value = "/update-delete-user/{username}", method = RequestMethod.POST)
    public String updateDeleteUser(@ModelAttribute("userForm") User updateUser,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin-update-delete-user";
        }
        User user = userRepository.findById(updateUser.getId()).get();
        userService.copyNecessaryUpdates(updateUser, user);
        userService.update(user);
        return "admin-update-delete-user";
    }

    @RequestMapping(value = "/update-delete-user/{username}", method = RequestMethod.DELETE)
    public String deleteUser(@ModelAttribute("userForm") User updateUser,
                                   BindingResult bindingResult, Model model) {
        User user = userRepository.findById(updateUser.getId()).get();
        userRepository.delete(user);
        return "admin-list-user";
    }

    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("userList", users);
        return "admin-list-user";
    }
}
