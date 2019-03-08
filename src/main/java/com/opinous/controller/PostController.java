package com.opinous.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/post")
@Controller
public class PostController {

    @PostMapping("/like/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void likePost(@PathVariable String id) {
        System.out.println(id + " is liked");
    }
}
