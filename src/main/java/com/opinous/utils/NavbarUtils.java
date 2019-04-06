package com.opinous.utils;

import org.springframework.ui.Model;

import com.opinous.constants.NavConstants;

public class NavbarUtils {
	public static void setNavPageActive(Model model, String page) {
		if(page.equals(NavConstants.ADMIN)) {
			model.addAttribute("isAdminNav", "active");
		} else if(page.equals(NavConstants.POPULAR)) {
			model.addAttribute("isPopularNav", "active");
		} else if(page.equals(NavConstants.RECENT)) {
			model.addAttribute("isRecentNav", "active");
		}
	}
}
