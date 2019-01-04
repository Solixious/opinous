package com.opinous.controller;


import com.opinous.enums.RoleConst;
import com.opinous.model.User;
import com.opinous.repository.UserRepository;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import com.opinous.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(AdminController.class);

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
        if(isAdmin()) {
            logger.debug("Going to admin control panel page.");
            return "admin-cp";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.GET)
    public String newUser(Model model) {
        if(isAdmin()) {
            model.addAttribute("userForm", new User());
            return "/admin-new-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/new-moderator", method = RequestMethod.GET)
    public String newModerator(Model model) {
        if(isAdmin()) {
            model.addAttribute("userForm", new User());
            return "/admin-new-moderator";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/new-admin", method = RequestMethod.GET)
    public String newAdmin(Model model) {
        if(isAdmin()) {
            model.addAttribute("userForm", new User());
            return "/admin-new-admin";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.POST)
    public String newUser(@ModelAttribute("userForm") User userForm,
                          BindingResult bindingResult, Model model) {
        if(isAdmin()) {

            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "admin-new-user";
            }

            userService.saveUser(userForm);
            model.addAttribute("userForm", new User());
            return "/admin-new-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/new-moderator", method = RequestMethod.POST)
    public String newModerator(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult, Model model) {
        if(isAdmin()) {

            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "admin-new-moderator";
            }
            RoleConst[] roles = new RoleConst[]
                    {
                            RoleConst.USER_ROLE,
                            RoleConst.MODERATOR_ROLE
                    };
            userService.saveUser(userForm, roles);
            model.addAttribute("userForm", new User());
            return "/admin-new-moderator";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/new-admin", method = RequestMethod.POST)
    public String newAdmin(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult, Model model) {
        if(isAdmin()) {

            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "admin-new-admin";
            }
            RoleConst[] roles = new RoleConst[]
                    {
                            RoleConst.USER_ROLE,
                            RoleConst.MODERATOR_ROLE,
                            RoleConst.ADMIN_ROLE
                    };
            userService.saveUser(userForm, roles);
            model.addAttribute("userForm", new User());
            return "/admin-new-admin";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }


    @RequestMapping(value = "/update-delete-user", method = RequestMethod.GET)
    public String updateDeleteUser(Model model) {
        if(isAdmin())
            return "admin-update-delete-user";
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/update-delete-user/{username}", method = RequestMethod.GET)
    public String updateDeleteUser(@PathVariable("username") String username, Model model) {
        if(isAdmin()) {
            User user = userRepository.findByUsername(username);

            if (user != null) {
                user = user.getCopy();
                user.setPassword("");
                user.setConfirmPassword("");
                model.addAttribute("userForm", user);
                return "admin-update-delete-user";
            } else {
                return "redirect:/admin/listUsers/" + username;
            }
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/update-delete-user/{username}", method = RequestMethod.POST)
    public String updateDeleteUser(@ModelAttribute("userForm") User updateUser,
                                   BindingResult bindingResult, Model model) {
        if(isAdmin()) {
            if (bindingResult.hasErrors()) {
                return "admin-update-delete-user";
            }

            User user = userRepository.findById(updateUser.getId()).get();
            userService.copyNecessaryUpdates(updateUser, user);
            userService.updateUser(user);
            return "admin-update-delete-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/update-delete-user/{username}", method = RequestMethod.DELETE)
    public String deleteUser(@ModelAttribute("userForm") User updateUser,
                                   BindingResult bindingResult, Model model) {
        if(isAdmin()) {
            User user = userRepository.findById(updateUser.getId()).get();
            userRepository.delete(user);
            return "redirect:/admin/listUsers";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public String listUsers(Model model) {
        if(isAdmin()) {
            List<User> users = userRepository.findAll();
            model.addAttribute("userList", users);
            return "/admin-list-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/listUsers/{username}", method = RequestMethod.GET)
    public String listUsers(Model model, @PathVariable("username") String username) {
        if(isAdmin()) {
            List<User> users = userRepository.findByUsernameIgnoreCaseContaining(username);
            model.addAttribute("userList", users);
            return "/admin-list-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    private boolean isAdmin() {
        return securityService.hasRole(RoleConst.ADMIN_ROLE.toString());
    }
}
