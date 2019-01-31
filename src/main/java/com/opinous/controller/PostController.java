package com.opinous.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opinous.constants.URLMappings;
import com.opinous.model.Post;
import com.opinous.service.AnonymousUserService;

@RequestMapping(value=URLMappings.POST)
@Controller
public class PostController {
	
	@Autowired
	private AnonymousUserService anonymousUserService;
	
	@PostMapping(value = URLMappings.POST_NEW)
	public String newPost(@ModelAttribute("postForm") Post post) {
		return null;
	}
}
