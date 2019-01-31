package com.opinous.controller;

import com.opinous.constants.JSPMapping;
import com.opinous.constants.URLMappings;
import com.opinous.model.Room;
import com.opinous.service.RoomService;
import com.opinous.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller @RequestMapping(URLMappings.ROOM)
public class RoomController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoomService roomService;

    @GetMapping(value = URLMappings.ROOM_NEW)
    public String newRoom(Model model) {
        if (securityService.isUser()) {
            model.addAttribute("roomForm", new Room());
            return JSPMapping.CREATE_NEW_ROOM;
        } else {
            return JSPMapping.LOGIN;
        }
    }

    @PostMapping(value = URLMappings.ROOM_NEW)
    public String newRoom(@ModelAttribute("roomForm") Room room, BindingResult bindingResult,
        Model model) {
        if (securityService.isUser()) {
            roomService.createRoom(room);
            model.addAttribute("roomForm", new Room());
            return "redirect:" + URLMappings.ROOM + "/" + room.getId();
        } else {
            return JSPMapping.LOGIN;
        }
    }

    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    public String viewRoom(@PathVariable("roomId") Long roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        return JSPMapping.ROOM_DETAILS;
    }
}
