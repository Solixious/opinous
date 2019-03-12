package com.opinous.controller;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.URLMapping;
import com.opinous.model.Room;
import com.opinous.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    RoomService roomService;

    @GetMapping(value = URLMapping.USER_HOME)
    public String welcome(Model model) {
        Page<Room> rooms = roomService.getRooms(0);
        model.addAttribute(AttributeName.ROOMS, rooms.getContent());
        model.addAttribute(AttributeName.PAGE_NUMBER, 0);
        model.addAttribute(AttributeName.MAX_PAGE_NUMBER, rooms.getTotalPages());
        return JSPMapping.HOME;
    }

    @GetMapping(value = URLMapping.USER_HOME_PAGINATED)
    public String homePaginated(@PathVariable String page, Model model) {
        try {
            int pageNo = Integer.parseInt(page) - 1;
            Page<Room> rooms = roomService.getRooms(pageNo);
            model.addAttribute(AttributeName.ROOMS, rooms.getContent());
            model.addAttribute(AttributeName.PAGE_NUMBER, pageNo);
            model.addAttribute(AttributeName.MAX_PAGE_NUMBER, rooms.getTotalPages());
        } catch (NumberFormatException e) {
        }
        return JSPMapping.HOME;
    }
}
