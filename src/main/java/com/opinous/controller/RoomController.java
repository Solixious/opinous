package com.opinous.controller;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.Misc;
import com.opinous.constants.URLMapping;
import com.opinous.exception.RoomOverloadedException;
import com.opinous.model.Alias;
import com.opinous.model.Post;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.service.AliasService;
import com.opinous.service.AnonymousUserService;
import com.opinous.service.PostService;
import com.opinous.service.RoomService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;

import com.opinous.validator.PostValidator;
import com.opinous.validator.RoomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(URLMapping.ROOM)
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
	private PostService postService;

	@Autowired
	private AliasService aliasService;

	@Autowired
	private RoomValidator roomValidator;

	@Autowired
	private PostValidator postValidator;

	@GetMapping(value = URLMapping.ROOM_NEW)
	public String newRoom(Model model) {
		if (securityService.isUser()) {
			model.addAttribute(AttributeName.ROOM_FORM, new Room());
			return JSPMapping.CREATE_NEW_ROOM;
		} else {
			return JSPMapping.LOGIN;
		}
	}

	@PostMapping(value = URLMapping.ROOM_NEW)
	public String newRoom(@ModelAttribute(AttributeName.ROOM_FORM) Room room, BindingResult bindingResult,
			Model model) throws RoomOverloadedException {
		roomValidator.validate(room, bindingResult);
		if (bindingResult.hasErrors()) {
			return JSPMapping.CREATE_NEW_ROOM;
		}

		if (securityService.isUser()) {
			roomService.createRoom(room);
			model.addAttribute(AttributeName.ROOM_FORM, new Room());
			return "redirect:" + URLMapping.ROOM + "/" + room.getId();
		} else {
			return JSPMapping.LOGIN;
		}
	}

	@GetMapping(value = "/{roomId}")
	public String viewRoom(@PathVariable("roomId") Long roomId, Model model) {
		final Room room = roomService.getRoomById(roomId);
		model.addAttribute(AttributeName.ROOM, roomService.convertToRoomDTO(room));
		model.addAttribute(AttributeName.POSTS, postService.getPostsByRoom(room));
		model.addAttribute(AttributeName.IS_USER, securityService.isUser());
		if (securityService.isUser()) {
			final User user = userService.getLoggedInUser();
			model.addAttribute(AttributeName.POST_FORM, new Post());
			final Alias alias = aliasService.getAliasByRoomAndUser(room, user);
			if (alias != null) {
				model.addAttribute(AttributeName.POSTING_AS, alias);
			}
		}
		return JSPMapping.ROOM_DETAILS;
	}

	@PostMapping(value = "/{roomId}")
	public String postReply(@ModelAttribute(AttributeName.POST_FORM) Post post, @PathVariable("roomId") Long roomId,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request) throws RoomOverloadedException  {
		postValidator.validate(post, bindingResult);
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", bindingResult);
			redirectAttributes.addFlashAttribute("post", post);
			return Misc.REDIRECT + URLMapping.ROOM + "/" + roomId;
		}

		final User user = userService.getLoggedInUser();
		final Room room = roomService.getRoomById(roomId);
		Alias alias = aliasService.getAliasByRoomAndUser(room, user);

		if (alias == null) {
			alias = aliasService.saveAlias(anonymousUserService.generateAnonymousUser(room), user,
				room);
		}

		post.setAlias(alias);
		postService.savePost(post);
		return Misc.REDIRECT + URLMapping.ROOM + "/" + room.getId();
	}
}
