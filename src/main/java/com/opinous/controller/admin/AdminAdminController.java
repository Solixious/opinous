package com.opinous.controller.admin;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminAdminController {

    private Logger logger = LoggerFactory.getLogger(AdminAdminController.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;


    @RequestMapping(value = "/new/admin", method = RequestMethod.GET)
    public String newAdmin(Model model) {
        if(securityService.isAdmin()) {
            model.addAttribute("userForm", new User());
            return "/admin-new-admin";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/new/admin", method = RequestMethod.POST)
    public String newAdmin(@ModelAttribute("userForm") User userForm,
                           BindingResult bindingResult, Model model) {
        if(securityService.isAdmin()) {

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
            notificationService.notify(model, NotificationType.success, "New administrator created successfully!");
            return "/admin-new-admin";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/list/admins", method = RequestMethod.GET)
    public String listAdmin(Model model) {
        if(securityService.isAdmin()) {
            List<String> roles = new ArrayList<>();
            roles.add(RoleConst.ADMIN_ROLE.toString());
            List<User> users = userRepository.findBySpecificRoles(roles);
            model.addAttribute("userList", users);
            return "/admin-list-admin";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }
}
