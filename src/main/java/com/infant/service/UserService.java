package com.infant.service;

import com.infant.entity.User;

public interface UserService {

	void signup(User user);

	User getUserByUsername(String username);

	void updateFCMToken(String fcmToken, String username);
}
