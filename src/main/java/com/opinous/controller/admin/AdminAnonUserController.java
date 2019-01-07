package com.opinous.controller.admin;

import com.opinous.enums.RoleConst;
import com.opinous.exception.FileStorageException;
import com.opinous.exception.MyFileNotFoundException;
import com.opinous.model.AnonymousUser;
import com.opinous.repository.AnonymousUserRepository;
import com.opinous.repository.UserRepository;
import com.opinous.service.FileStorageService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import com.opinous.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/admin")
public class AdminAnonUserController {

    private Logger logger = LoggerFactory.getLogger(AdminAnonUserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AnonymousUserRepository anonymousUserRepository;


    @RequestMapping(value = "/new/anon", method = RequestMethod.GET)
    public String newAnonUser(Model model) {
        if(securityService.isAdmin()) {
            model.addAttribute("userForm", new AnonymousUser());
            return "/admin-new-anon-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/new/anon", method = RequestMethod.POST)
    public String newAnonUser(@RequestParam("file") MultipartFile file,
                              @ModelAttribute("userForm") AnonymousUser userForm,
                              Model model) throws FileStorageException {
        if(securityService.isAdmin()) {
            String suggestedFileName = userForm.getName() + " " + new Random().nextInt(9999)
                    + " " +  file.getOriginalFilename().substring(
                            file.getOriginalFilename().lastIndexOf('.'));
            String fileName = fileStorageService.storeFile(file, suggestedFileName);
            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/file/")
                    .path(fileName)
                    .toUriString();
            userForm.setDisplayPicture(uri);
            anonymousUserRepository.save(userForm);
            return "/admin-new-anon-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @RequestMapping(value = "/list/anon", method = RequestMethod.GET)
    public String listAnonUser(Model model) {
        if(securityService.isAdmin()) {
            List<AnonymousUser> anonymousUsers = anonymousUserRepository.findAll();

            model.addAttribute("userList", anonymousUsers);
            return "admin-list-anon-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }
}
