package com.infant.service;

import com.infant.dto.UserDTO;
import com.infant.entity.Userv2;
import java.util.List;

/**
 * Created by sujith on 04-11-2023
 */
public interface RegistrationService {

  boolean checkUserNameIsValid(String username);

  void createUser(UserDTO userDTO);

  List<UserDTO> getAllUsers();

  void updateStatus(Long userId, String approved);

  UserDTO getUser(Long userId);

  void updateUser(UserDTO userDTO);

  void deleteUser(Long userId);

  Userv2 login(String username, String password);

  boolean veryfyOtp(String username, String otp);

  String generateOtp(String username);
}
