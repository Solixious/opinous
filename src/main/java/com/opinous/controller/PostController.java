package com.opinous.controller;

import com.opinous.exception.RoomOverloadedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.opinous.enums.ReactionType;
import com.opinous.service.ReactionService;
import com.opinous.service.SecurityService;

@RequestMapping("/post")
@Controller
public class PostController {

	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private SecurityService securityService;

    @PostMapping("/like/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void likePost(@PathVariable String id) throws RoomOverloadedException {
        if(securityService.isUser()) {
            reactionService.addReaction(ReactionType.LIKE, id);
        }
    }

    @PostMapping("/dislike/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void dislikePost(@PathVariable String id) throws RoomOverloadedException {
        if(securityService.isUser()) {
            reactionService.addReaction(ReactionType.DISLIKE, id);
        }
    }

    @PostMapping("/unlike/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void unlikePost(@PathVariable String id) {
        if(securityService.isUser()) {
            reactionService.removeReaction(ReactionType.LIKE, id);
        }
    }

    @PostMapping("/undislike/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void undislikePost(@PathVariable String id) {
        if(securityService.isUser()) {
            reactionService.removeReaction(ReactionType.DISLIKE, id);
        }
    }
}
