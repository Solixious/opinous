package com.opinous.service.impl;

import com.opinous.model.CustomUserDetail;
import com.opinous.model.Role;
import com.opinous.model.User;
import com.opinous.repository.UserRepository;
import com.opinous.utils.PreCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public CustomUserDetail loadUserByUsername(final String username) throws UsernameNotFoundException {
		PreCondition.checkNotNull(username, "username");
		final User user = userRepository.findByUsername(username);
		final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		final CustomUserDetail userDetail = new CustomUserDetail(user.getUsername(), user.getPassword(),
				grantedAuthorities);
		userDetail.setFirstName(user.getFirstName());
		userDetail.setLastName(user.getLastName());
		return userDetail;
	}
}
