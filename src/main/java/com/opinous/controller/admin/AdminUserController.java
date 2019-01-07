package com.opinous.controller.admin;

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
public class AdminUserController {
    private Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String adminHome(HttpServletRequest request) {
        if(securityService.isAdmin()) {
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
        if(securityService.isAdmin()) {
            model.addAttribute("userForm", new User());
            return "/admin-new-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.POST)
    public String newUser(@ModelAttribute("userForm") User userForm,
                          BindingResult bindingResult, Model model) {
        if(securityService.isAdmin()) {

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

    @RequestMapping(value = "/update-delete-user", method = RequestMethod.GET)
    public String updateDeleteUser(Model model) {
        if(securityService.isAdmin())
            return "admin-update-delete-user";
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/update-delete-user/{username}", method = RequestMethod.GET)
    public String updateDeleteUser(@PathVariable("username") String username, Model model) {
        if(securityService.isAdmin()) {
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
        if(securityService.isAdmin()) {
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
        if(securityService.isAdmin()) {
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
        if(securityService.isAdmin()) {
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
        if(securityService.isAdmin()) {
            List<User> users = userRepository.findByUsernameIgnoreCaseContaining(username);
            model.addAttribute("userList", users);
            return "/admin-list-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }
}
