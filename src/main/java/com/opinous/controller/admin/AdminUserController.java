package com.opinous.controller.admin;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.Misc;
import com.opinous.constants.URLMapping;
import com.opinous.enums.NotificationType;
import com.opinous.model.User;
import com.opinous.repository.UserRepository;
import com.opinous.service.NotificationService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import com.opinous.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(URLMapping.ADMIN)
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

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = URLMapping.USER_HOME)
    public String adminHome(HttpServletRequest request) {
        if (securityService.isAdmin()) {
            logger.debug("Going to admin control panel page.");
            return JSPMapping.ADMIN_CONTROL_PANEL;
        } else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }

    @GetMapping(value = URLMapping.NEW_USER)
    public String newUser(Model model) {
        if (securityService.isAdmin()) {
            model.addAttribute(AttributeName.USER_FORM, new User());
            return JSPMapping.ADMIN_NEW_USER;
        } else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }

    @PostMapping(value = URLMapping.NEW_USER)
    public String newUser(@ModelAttribute(AttributeName.USER_FORM) User userForm, BindingResult bindingResult,
        Model model) {
        if (securityService.isAdmin()) {

            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return JSPMapping.ADMIN_NEW_USER;
            }

            userService.saveUser(userForm);
            model.addAttribute(AttributeName.USER_FORM, new User());
            notificationService
                .notify(model, NotificationType.success, "Created new user successfully!");
            return JSPMapping.ADMIN_NEW_USER;
        } else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }

    @GetMapping(value = URLMapping.UPDATE_USER)
    public String updateDeleteUser(Model model) {
        if (securityService.isAdmin())
            return JSPMapping.ADMIN_UPDATE_DELETE_USER;
        else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }

    @GetMapping(value = URLMapping.UPDATE_USER + "/{username}")
    public String updateDeleteUser(@PathVariable("username") String username, Model model) {
        if (securityService.isAdmin()) {
            User user = userRepository.findByUsername(username);

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
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }

    @PostMapping(value = URLMapping.UPDATE_USER + "/{username}")
    public String updateDeleteUser(@ModelAttribute(AttributeName.USER_FORM) User updateUser,
        BindingResult bindingResult, Model model) {
        if (securityService.isAdmin()) {
            if (bindingResult.hasErrors()) {
                return JSPMapping.ADMIN_UPDATE_DELETE_USER;
            }

            User user = userRepository.findById(updateUser.getId()).get();
            userService.copyNecessaryUpdates(updateUser, user);
            userService.updateUser(user);
            notificationService
                .notify(model, NotificationType.success, "User details updated successfully!");
            return JSPMapping.ADMIN_UPDATE_DELETE_USER;
        } else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }

    @DeleteMapping(value = URLMapping.UPDATE_USER + "/{username}")
    public String deleteUser(@ModelAttribute(AttributeName.USER_FORM) User updateUser,
        BindingResult bindingResult, Model model) {
        if (securityService.isAdmin()) {
            User user = userRepository.findById(updateUser.getId()).get();
            userRepository.delete(user);
            notificationService.notify(model, NotificationType.success,
                "The user concerned deleted successfully!");
            return "redirect:" + URLMapping.ADMIN + URLMapping.LIST_USER;
        } else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }

    @RequestMapping(value = URLMapping.LIST_USER, method = RequestMethod.GET)
    public String listUsers(Model model) {
        if (securityService.isAdmin()) {
            List<User> users = userRepository.findAll();
            model.addAttribute(AttributeName.USER_LIST, users);
            return JSPMapping.ADMIN_LIST_USER;
        } else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }

    @GetMapping(value = URLMapping.LIST_USER + "/{username}")
    public String listUsers(Model model, @PathVariable("username") String username) {
        if (securityService.isAdmin()) {
            List<User> users = userRepository.findByUsernameIgnoreCaseContaining(username);
            model.addAttribute(AttributeName.USER_LIST, users);
            return JSPMapping.ADMIN_LIST_USER;
        } else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }
}
