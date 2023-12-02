package com.infant.service;

import com.infant.dto.UserDTO;
import com.infant.entity.Userv2;
import com.infant.repository.UserV2Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Created by sujith on 04-11-2023
 */

@Service
public class RegistrationServiceImpl implements RegistrationService{

  @Autowired
  private UserV2Repository userV2Repository;

  @Autowired
  private TwilioService twilioService;

  //TODO move to application.properties or db
  private static final String REGISTRATION_MESSAGE = "Hi Admin, a new user with the username XXXXX has signed up for an account. Please approve the user.";
  private static final String LOGIN_OTP = "Hi, XXXXX. Here is your login OTP ";


  @Override
  public boolean checkUserNameIsValid(String username) {
   Userv2 userv2 =  userV2Repository.findByUsername(username);
   if(userv2 == null)
     return true;
    return false;
  }

  @Override
  public void createUser(UserDTO userDTO) {
    Userv2 userv2 = new Userv2();
    userv2.setUsername(userDTO.getUsername());
    userv2.setName(userDTO.getName());
    userv2.setPhone(userDTO.getMobileNumber());
    userv2.setPassword(userDTO.getPassword());
    userv2.setStatus("Pending");
    userv2.setRole("User");
    userV2Repository.save(userv2);
    String message = REGISTRATION_MESSAGE.replace("XXXXX", userDTO.getUsername());
    twilioService.sendSms(userDTO.getMobileNumber(), message);
  }

  @Override
  public List<UserDTO> getAllUsers() {
    List<Userv2> userList = userV2Repository.findAll();
    ArrayList<UserDTO> userDTOList = new ArrayList<>();
    if(!CollectionUtils.isEmpty(userList)) {
      for(Userv2 userV2 : userList) {
        UserDTO userDTO = new UserDTO();
        userDTO.setMobileNumber(userV2.getPhone());
        userDTO.setName(userV2.getName());
        userDTO.setUsername(userV2.getUsername());
        userDTO.setStatus(userV2.getStatus());
        userDTO.setId(userV2.getId());
        userDTOList.add(userDTO);
      }
    }
    return  userDTOList;
  }

  @Override
  public void updateStatus(Long userId, String approved) {
    Userv2 userv2 = userV2Repository.findById(userId).get();
    userv2.setStatus("Approved");
    userV2Repository.save(userv2);
  }

  @Override
  public UserDTO getUser(Long userId) {
    Userv2 userV2 = userV2Repository.findById(userId).get();
    UserDTO userDTO = new UserDTO();
    userDTO.setMobileNumber(userV2.getPhone());
    userDTO.setName(userV2.getName());
    userDTO.setUsername(userV2.getUsername());
    userDTO.setStatus(userV2.getStatus());
    userDTO.setId(userV2.getId());

    return  userDTO;
  }

  @Override
  public void updateUser(UserDTO userDTO) {
    Userv2 userv2 = userV2Repository.findById(userDTO.getId()).get();
    userv2.setName(userDTO.getName());
    userv2.setPhone(userDTO.getMobileNumber());
    userV2Repository.save(userv2);
  }

  @Override
  public void deleteUser(Long userId) {
    Userv2 userv2 = userV2Repository.findById(userId).get();
    userV2Repository.delete(userv2);
  }

  @Override
  public Userv2 login(String username, String password) {
    Userv2 userv2 = userV2Repository
        .findByUsernameAndPasswordAndStatus(username, password, "Approved");
    if(userv2 == null) {
      return null;
    }

    String otp = generateOtp();
    userv2.setOtp(otp);
    userV2Repository.save(userv2);
    String message = LOGIN_OTP.replace("XXXXX", userv2.getUsername());
    twilioService.sendSms(userv2.getPhone(), message + otp);
    return userv2;
  }

  private String generateOtp() {
    // Define the length of the OTP
    int otpLength = 6;

    // Generate a random 6-digit OTP
    Random random = new Random();
    StringBuilder otpBuilder = new StringBuilder();

    for (int i = 0; i < otpLength; i++) {
      int digit = random.nextInt(10); // Generates a random digit (0-9)
      otpBuilder.append(digit);
    }
    return otpBuilder.toString();
  }

  @Override
  public boolean veryfyOtp(String username, String otp) {
    Userv2 userv2 = userV2Repository.findByUsername(username);
    if(userv2 == null)
      return  false;
    if(userv2.getOtp().equals(otp))
      return true;
    return  false;
  }

  @Override
  public String generateOtp(String username) {
    String otp = generateOtp();
    Userv2 userv2 = userV2Repository.findByUsername(username);
    userv2.setOtp(otp);
    userV2Repository.save(userv2);
    String message = LOGIN_OTP.replace("XXXXX", userv2.getUsername());
    twilioService.sendSms(userv2.getPhone(), message + otp);
    return otp;
  }
}
