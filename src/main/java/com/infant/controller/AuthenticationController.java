package com.infant.controller;

import com.infant.component.JwtTokenComponent;
import com.infant.entity.User;
import com.infant.request.AuthenticationRequest;
import com.infant.response.AuthenticationResponse;
import com.infant.service.AuthenticationUserDetailService;
import com.infant.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenComponent jwtTokenComponent;

	@Autowired
	private AuthenticationUserDetailService userDetailService;
	
	@Autowired
	private UserService userService;

	@PostMapping("authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		Authentication authenticate = null;
		try {
			authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		User user = null;
				UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtTokenComponent.generateToken(userDetails);
		if(authenticate.isAuthenticated() && StringUtils.isNotBlank(token)) {
			userService.updateFCMToken(authenticationRequest.getFcmToken(), authenticationRequest.getUsername()) ;
			user = userService.getUserByUsername(authenticationRequest.getUsername());
		}
		return ResponseEntity.ok(new AuthenticationResponse(token, user.getId()));
	}
}