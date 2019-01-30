package com.opinous.controller;

import com.opinous.model.Room;
import com.opinous.service.RoomService;
import com.opinous.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoomService roomService;

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

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newRoom(@ModelAttribute("roomForm") Room room, BindingResult bindingResult, Model model) {
        if(securityService.isUser()) {
            roomService.createRoom(room);
            model.addAttribute("roomForm", new Room());
            return "create-new-room";
        }
        else {
            return "login";
        }
    }
}
