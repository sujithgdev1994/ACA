package com.infant.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.infant.entity.User;
import com.infant.request.UserRequest;

@Component
public class UserRequestToUserEntityConverter implements Converter<UserRequest, User> {

	@Override
	public User convert(UserRequest source) {
		User user = new User();
		user.setPassword(source.getPassword());
		user.setUsername(source.getUsername());
		return user;
	}

}
