package com.infant.controller;

import com.infant.dto.UserDTO;
import com.infant.entity.Userv2;
import com.infant.response.BasicResponse;
import com.infant.service.RegistrationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sujith on 13-11-2023
 */
@RestController
@RequestMapping("v1")
public class ACAController {

  @Autowired
  private RegistrationService registrationService;

  @PostMapping("/register")
  public ResponseEntity<BasicResponse> processRegistration(@RequestBody UserDTO userDTO) {
    boolean valid = registrationService.checkUserNameIsValid(userDTO.getUsername());
    if (!valid) {
      return ResponseEntity.status(HttpStatusCode.valueOf(400))
          .body(new BasicResponse("failed", "username already exists"));
    }
    registrationService.createUser(userDTO);
    return ResponseEntity.status(HttpStatusCode.valueOf(201))
        .body(new BasicResponse("success", "Registration successfully"));
  }

  @PostMapping("/login")
  public ResponseEntity<BasicResponse> login(@RequestParam("username") String username,
      @RequestParam("password") String password,
      HttpSession session) {
    Userv2 login = registrationService.login(username, password);
    if (login == null) {
      return ResponseEntity.status(HttpStatusCode.valueOf(400))
          .body(new BasicResponse("failed", "invalid credentials"));
    }
    return ResponseEntity.status(HttpStatusCode.valueOf(200))
        .body(new BasicResponse("success", "User valid"));
  }

  @PostMapping("/verify-otp")
  public ResponseEntity<BasicResponse> verifyOtp(@RequestParam("username") String username, @RequestParam("otp") String otp) {
    boolean isSuccess = registrationService.veryfyOtp(username, otp);
    if (isSuccess) {
      return ResponseEntity.status(HttpStatusCode.valueOf(200))
          .body(new BasicResponse("success", "OTP valid"));
    }
    return ResponseEntity.status(HttpStatusCode.valueOf(400))
        .body(new BasicResponse("failed", "Invalid OTP"));
  }

  @GetMapping("/generate-otp")
  public ResponseEntity<BasicResponse> generateOtp(@RequestParam("username") String username) {
    registrationService.generateOtp(username);
    return ResponseEntity.status(HttpStatusCode.valueOf(200))
        .body(new BasicResponse("success", "success"));
  }

}


