package com.opinous.controller;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.URLMapping;
import com.opinous.exception.FileStorageException;
import com.opinous.model.User;
import com.opinous.repository.UserRepository;
import com.opinous.service.FileStorageService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Random;

@Slf4j
@RequestMapping(URLMapping.PROFILE)
@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
		private FileStorageService fileStorageService;

    @GetMapping(URLMapping.USER_PROFILE_BASIC)
    public String basic(Model model) {
    	User user = userService.findByUsername(securityService.findLoggedInUsername());
    	user.setPassword("");
        model.addAttribute(AttributeName.USER_DETAIL, user);
        return JSPMapping.USER_PROFILE_BASIC;
    }
    
    @PostMapping(URLMapping.USER_PROFILE_BASIC)
    public String basic(@RequestParam("file") MultipartFile file,
			@ModelAttribute(AttributeName.USER_DETAIL) User updateUser, BindingResult bindingResult,
			Model model) throws FileStorageException {
    	if (securityService.isUser()) {
			if (bindingResult.hasErrors()) {
				return JSPMapping.USER_PROFILE_BASIC;
			}

			User user = userRepository.findById(updateUser.getId()).get();
			userService.copyNecessaryUpdates(updateUser, user);

				if (file != null && file.getOriginalFilename().length() > 0) {
					String suggestedFileName = user.getUsername() + " " + new Random().nextInt(9999) + " "
						+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
					String fileName = fileStorageService.storeFile(file, suggestedFileName);
					String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/").path(fileName)
						.toUriString();
					user.setProfilePicture(uri);
				}

			userService.updateUser(user);
			user.setPassword("");
	        model.addAttribute(AttributeName.USER_DETAIL, user);
			return JSPMapping.USER_PROFILE_BASIC;
		} else {
			log.error("Illegal attempt to update user details.");
			return JSPMapping.ERROR;
		}

    }
}
