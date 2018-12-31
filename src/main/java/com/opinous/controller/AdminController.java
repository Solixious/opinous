package com.opinous.controller;


import com.opinous.enums.RoleConst;
import com.opinous.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String adminHome(HttpServletRequest request) {
        if(securityService.hasRole(RoleConst.ADMIN_ROLE.toString()))
            return "admincp";
        else
            return "error";
    }
}
