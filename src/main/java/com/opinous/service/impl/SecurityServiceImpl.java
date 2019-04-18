package com.opinous.service.impl;

import com.opinous.enums.RoleConst;
import com.opinous.model.CustomUserDetail;
import com.opinous.service.SecurityService;
import com.opinous.utils.PreCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public String findLoggedInUsername() {
		final Object userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof CustomUserDetail) {
			return ((CustomUserDetail) userDetails).getUsername();
		}
		return null;
	}

	@Override
	public void autologin(final String username, final String password) {
		PreCondition.checkNotNull(username, "username");
		PreCondition.checkNotNull(password, "password");
		final CustomUserDetail userDetails = (CustomUserDetail) userDetailsService.loadUserByUsername(username);
		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, password, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	}

	@Override
	public boolean hasRole(final String role) {
		PreCondition.checkNotNull(role, "role");
		final SecurityContext context = SecurityContextHolder.getContext();
		if (context == null)
			return false;

		final Authentication authentication = context.getAuthentication();
		if (authentication == null)
			return false;

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if (role.equals(auth.getAuthority()))
				return true;
		}

		return false;
	}

	@Override
	public boolean isAdmin() {
		return hasRole(RoleConst.ADMIN_ROLE.toString());
	}

	@Override
	public boolean isUser() {
		return hasRole(RoleConst.USER_ROLE.toString());
	}

	@Override
	public boolean isModerator() {
		return hasRole(RoleConst.MODERATOR_ROLE.toString());
	}
}
