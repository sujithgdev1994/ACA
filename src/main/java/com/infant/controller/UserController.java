package com.infant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infant.converter.UserRequestToUserEntityConverter;
import com.infant.entity.User;
import com.infant.request.UserRequest;
import com.infant.response.BasicResponse;
import com.infant.service.UserService;

//@RestController
@RequestMapping("v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRequestToUserEntityConverter userRequestToUserEntityConverter;

	@PostMapping("signup")
	public ResponseEntity<BasicResponse> signupUser(@RequestBody UserRequest userRequest) {
		User user = userRequestToUserEntityConverter.convert(userRequest);
		userService.signup(user);
		return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CREATED.value()))
				.body(new BasicResponse("Status", "User created successfully"));
	}

	@GetMapping("{username}")
	public User getUser(@PathVariable String username) {
	return 	userService.getUserByUsername(username);
	}

}