package com.infant.service;

import com.infant.entity.MotherEntity;
import com.infant.entity.User;
import com.infant.exception.UsernameExistException;
import com.infant.repository.MotherRepository;
import com.infant.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MotherRepository motherRepository;

	@Override
	public void signup(User user) {
		Optional<User> optionalUsername = userRepository.findByUsername(user.getUsername());
		if (optionalUsername.isPresent()) {
			throw new UsernameExistException("Username is already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		MotherEntity motherEntity = new MotherEntity();
		motherEntity.setUser(user);
		motherEntity.setName(user.getUsername());
		motherRepository.save(motherEntity);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Override
	public void updateFCMToken(String fcmToken, String username) {
		User user = getUserByUsername(username);
		user.setFcmToken(fcmToken);
		userRepository.save(user);
	}
}
