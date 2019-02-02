package com.opinous.controller;

import com.opinous.constants.JSPMapping;
import com.opinous.constants.URLMappings;
import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Post;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.AnonMapRepository;
import com.opinous.repository.PostRepository;
import com.opinous.service.AnonymousUserService;
import com.opinous.service.RoomService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;

import java.util.List;

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
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AnonymousUserService anonymousUserService;
    
    @Autowired
    private AnonMapRepository anonMapRepository;
    
    @Autowired
    private PostRepository postRepository;

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
        
        List<AnonMap> anonMaps = anonMapRepository.findByRoom(room);
        List<Post> posts = postRepository.findByAnonMapIn(anonMaps);
        model.addAttribute("posts", posts);
        
        model.addAttribute("isUser", securityService.isUser());
        if(securityService.isUser()) {
        	model.addAttribute("postForm", new Post());
        }
        return JSPMapping.ROOM_DETAILS;
    }
    
    @RequestMapping(value = "/{roomId}", method = RequestMethod.POST)
    public String postReply(@ModelAttribute("postForm") Post post, @PathVariable("roomId") Long roomId, Model model) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        Room room = roomService.getRoomById(roomId);
        AnonMap anonMap = anonMapRepository.findByRoomAndUser(room, user);
        
        if(anonMap == null) {
        	AnonymousUser anonymousUser = anonymousUserService.generateAnonymousUser(room);
        	anonMap = new AnonMap();
        	anonMap.setAnonymousUser(anonymousUser);
        	anonMap.setUser(user);
        	anonMap.setRoom(room);
        	anonMapRepository.save(anonMap);
        }
        
        post.setAnonMap(anonMap);
        postRepository.save(post);
        return "redirect:" + URLMappings.ROOM + "/" + room.getId();
    }
}
