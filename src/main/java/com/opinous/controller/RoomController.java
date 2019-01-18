package com.opinous.controller;

import com.opinous.model.Room;
import com.opinous.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRoom(Model model) {
        if(securityService.isUser()) {
            model.addAttribute("roomForm", new Room());
            return "create-new-room";
        }
        else {
            return "login";
        }
    }
}
