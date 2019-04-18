package com.opinous.controller;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.NavConstants;
import com.opinous.constants.URLMapping;
import com.opinous.model.Room;
import com.opinous.model.dto.RoomDTO;
import com.opinous.service.RoomService;
import com.opinous.utils.NavbarUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private RoomService roomService;

    @GetMapping(value = URLMapping.USER_HOME)
    public String welcome(Model model) {
        final Page<Room> rooms = roomService.getRooms(0);
        final List<RoomDTO> roomList = roomService.convertToRoomDTO(rooms.getContent());
        model.addAttribute(AttributeName.ROOMS, roomList);
        model.addAttribute(AttributeName.PAGE_NUMBER, 0);
        model.addAttribute(AttributeName.MAX_PAGE_NUMBER, rooms.getTotalPages());
        NavbarUtils.setNavPageActive(model, NavConstants.POPULAR);
        return JSPMapping.HOME;
    }

    @GetMapping(value = URLMapping.USER_HOME_PAGINATED)
    public String homePaginated(@PathVariable String page, Model model) {
        try {
            final int pageNo = Integer.parseInt(page) - 1;
            final Page<Room> rooms = roomService.getRooms(pageNo);
            final List<RoomDTO> roomList = roomService.convertToRoomDTO(rooms.getContent());
            model.addAttribute(AttributeName.ROOMS, roomList);
            model.addAttribute(AttributeName.PAGE_NUMBER, pageNo);
            model.addAttribute(AttributeName.MAX_PAGE_NUMBER, rooms.getTotalPages());
            NavbarUtils.setNavPageActive(model, NavConstants.POPULAR);
        } catch (NumberFormatException e) {
        }
        return JSPMapping.HOME;
    }
    
    @GetMapping(value = URLMapping.RECENT)
    public String recent(Model model) {
        final Page<Room> rooms = roomService.getRooms(0);
        final List<RoomDTO> roomList = roomService.convertToRoomDTO(rooms.getContent());
        model.addAttribute(AttributeName.ROOMS, roomList);
        model.addAttribute(AttributeName.PAGE_NUMBER, 0);
        model.addAttribute(AttributeName.MAX_PAGE_NUMBER, rooms.getTotalPages());
        NavbarUtils.setNavPageActive(model, NavConstants.RECENT);
        return JSPMapping.RECENT;
    }

    @GetMapping(value = URLMapping.RECENT_PAGINATED)
    public String recentPaginated(@PathVariable String page, Model model) {
        try {
            final int pageNo = Integer.parseInt(page) - 1;
            final Page<Room> rooms = roomService.getRooms(pageNo);
            final List<RoomDTO> roomList = roomService.convertToRoomDTO(rooms.getContent());
            model.addAttribute(AttributeName.ROOMS, roomList);
            model.addAttribute(AttributeName.PAGE_NUMBER, pageNo);
            model.addAttribute(AttributeName.MAX_PAGE_NUMBER, rooms.getTotalPages());
            NavbarUtils.setNavPageActive(model, NavConstants.RECENT);
        } catch (NumberFormatException e) {
        }
        return JSPMapping.RECENT;
    }
}
