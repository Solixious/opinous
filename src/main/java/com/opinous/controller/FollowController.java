package com.opinous.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.opinous.service.FollowService;
import com.opinous.service.UserService;

@Controller
public class FollowController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FollowService followService;
	
	@PostMapping("/follow/{username}")
	@ResponseStatus(value = HttpStatus.OK)
	public void follow(@PathVariable("username") String username) {
		followService.follow(userService.findByUsername(username));
	}
	
	@PostMapping("/unfollow/{username}")
	@ResponseStatus(value = HttpStatus.OK)
	public void unfollow(@PathVariable("username") String username) {
		followService.unfollow(userService.findByUsername(username));
	}
}
