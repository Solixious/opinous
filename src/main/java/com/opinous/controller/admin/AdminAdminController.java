package com.opinous.controller.admin;

import com.opinous.constants.JSPMapping;
import com.opinous.constants.URLMappings;
import com.opinous.enums.NotificationType;
import com.opinous.enums.RoleConst;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller @RequestMapping(URLMappings.ADMIN) public class AdminAdminController {

    private Logger logger = LoggerFactory.getLogger(AdminAdminController.class);

    @Autowired private SecurityService securityService;

    @Autowired private UserService userService;

    @Autowired private UserValidator userValidator;

    @Autowired private UserRepository userRepository;

    @Autowired private NotificationService notificationService;


    @GetMapping(value = URLMappings.NEW_ADMIN) public String newAdmin(Model model) {
        if (securityService.isAdmin()) {
            model.addAttribute("userForm", new User());
            return JSPMapping.ADMIN_NEW_ADMIN;
        } else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }

    @PostMapping(value = URLMappings.NEW_ADMIN)
    public String newAdmin(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
        Model model) {
        if (securityService.isAdmin()) {

            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return JSPMapping.ADMIN_NEW_ADMIN;
            }
            RoleConst[] roles = new RoleConst[] {RoleConst.USER_ROLE, RoleConst.MODERATOR_ROLE,
                RoleConst.ADMIN_ROLE};
            userService.saveUser(userForm, roles);
            model.addAttribute("userForm", new User());
            notificationService
                .notify(model, NotificationType.success, "New administrator created successfully!");
            return JSPMapping.ADMIN_NEW_ADMIN;
        } else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }

    @GetMapping(value = URLMappings.LIST_ADMINS) public String listAdmin(Model model) {
        if (securityService.isAdmin()) {
            List<String> roles = new ArrayList<>();
            roles.add(RoleConst.ADMIN_ROLE.toString());
            List<User> users = userRepository.findBySpecificRoles(roles);
            model.addAttribute("userList", users);
            return JSPMapping.ADMIN_LIST_ADMIN;
        } else {
            logger.error("Illegal attempt to access admin page");
            return JSPMapping.ERROR;
        }
    }
}
