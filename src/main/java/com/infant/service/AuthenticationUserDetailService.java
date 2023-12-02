package com.infant.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.infant.entity.User;
import com.infant.repository.UserRepository;

@Service
public class AuthenticationUserDetailService implements UserDetailsService {

	private static final String DEFAULT_ROLE_PREFIX = "ROLE_";

	private static final String USER_ROLE = "User";

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid user"));
		// Will change future, As of now only one role
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(DEFAULT_ROLE_PREFIX + USER_ROLE)));
	}

}