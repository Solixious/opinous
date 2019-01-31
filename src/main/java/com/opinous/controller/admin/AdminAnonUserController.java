package com.opinous.controller.admin;

import com.opinous.constants.URLMappings;
import com.opinous.enums.NotificationType;
import com.opinous.exception.FileStorageException;
import com.opinous.model.AnonymousUser;
import com.opinous.repository.AnonymousUserRepository;
import com.opinous.service.FileStorageService;
import com.opinous.service.NotificationService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(URLMappings.ADMIN)
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

    @Autowired
    private NotificationService notificationService;


    @GetMapping(value = URLMappings.NEW_ANON)
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

    @PostMapping(value = URLMappings.NEW_ANON)
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
            notificationService.notify(model, NotificationType.success, "Created new anonymous user successfully!");
            return "/admin-new-anon-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @GetMapping(value = URLMappings.UPDATE_ANON)
    public String updateAnonUser() {
        if(securityService.isAdmin()) {
            return "admin-update-anon-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @GetMapping(value = URLMappings.UPDATE_ANON + "/{name}")
    public String updateAnonUser(@PathVariable("name") String name, Model model) {
        if(securityService.isAdmin()) {
            AnonymousUser user = anonymousUserRepository.findByName(name);
            model.addAttribute("userForm", user);
            return "admin-update-anon-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @PostMapping(value = URLMappings.UPDATE_ANON + "/{name}")
    public String updateAnonUser(@RequestParam("file") MultipartFile file,
                                 @ModelAttribute("userForm") AnonymousUser updateUser,
                                 Model model) throws FileStorageException {
        if(securityService.isAdmin()) {
            AnonymousUser user = anonymousUserRepository.findById(updateUser.getId()).get();
            user.setName(updateUser.getName());
            if(file != null && file.getOriginalFilename().length() > 0) {
                String suggestedFileName = updateUser.getName() + " " + new Random().nextInt(9999)
                        + " " +  file.getOriginalFilename().substring(
                        file.getOriginalFilename().lastIndexOf('.'));
                String fileName = fileStorageService.storeFile(file, suggestedFileName);
                String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/file/")
                        .path(fileName)
                        .toUriString();
                user.setDisplayPicture(uri);
            }
            anonymousUserRepository.save(user);
            notificationService.notify(model, NotificationType.success, "Updated anonymous user details successfully!");
            return "admin-update-anon-user";
        }
        else {
            logger.error("Illegal attempt to access admin page");
            return "error";
        }
    }

    @GetMapping(value = URLMappings.LIST_ANON)
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
