package com.infant.controller;

import com.infant.dto.UserDTO;
import com.infant.entity.Userv2;
import com.infant.service.RegistrationService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

  @Autowired
  private RegistrationService registrationService;

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new UserDTO());
    return "registration";
  }

  @PostMapping("/register")
  public String processRegistration(@ModelAttribute("user") UserDTO userDTO) {
    boolean valid = registrationService.checkUserNameIsValid(userDTO.getUsername());
    if (!valid) {
      return "redirect:/errordis?errorMessage=Username already exists";
    }
    registrationService.createUser(userDTO);
    return "registration-success";
  }

  @GetMapping("/errordis")
  public String showErrorPage(@RequestParam(name = "errorMessage", required = false) String errorMessage,
      Model model) {
    model.addAttribute("errorMessage", errorMessage);
    return "error";
  }

  @GetMapping("/user-all")
  public String showUserList(Model model) {
    List<UserDTO> userList = registrationService.getAllUsers();
    model.addAttribute("users", userList);
    return "user-list";
  }

  @GetMapping("/login")
  public String showLoginForm() {
    return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam("username") String username, @RequestParam("password") String password,
      HttpSession session) {
    Userv2 login = registrationService.login(username, password);
    if (login == null) {
      return "redirect:/errordis?errorMessage=Invalid credentials";
    }

    session.setAttribute("role", login.getRole());
    session.setAttribute("username", login.getUsername());

    return "redirect:/otp";
  }

  @PostMapping("/updateStatus")
  public String updateStatus(@RequestParam("userId") Long userId) {
    registrationService.updateStatus(userId, "Approved");
    return "redirect:/user-all";
  }

  @GetMapping("/edit-user/{userId}")
  public String showEditForm(@PathVariable Long userId, Model model) {
    UserDTO userDTO = registrationService.getUser(userId);
    model.addAttribute("user", userDTO);
    return "edit-user";
  }

  @GetMapping("/")
  public String basePage() {
    return "login";
  }

  @PostMapping("/update-user")
  public String updateUser(@RequestParam("userId") Long userId, UserDTO user) {
    registrationService.getUser(userId);
    user.setId(userId);
    registrationService.updateUser(user);
    return "redirect:/user-all";
  }

  @GetMapping("/delete-user/{userId}")
  public String deleteUser(@PathVariable Long userId) {
    registrationService.deleteUser(userId);
    return "redirect:/user-all";
  }

  @GetMapping("/otp")
  public String otp(Model model, HttpSession session) {
    return "otp";
  }

  @PostMapping("/verify-otp")
  public String verifyOtp(@RequestParam("username") String username, @RequestParam("otp") String otp,
      @RequestParam("role") String role, HttpSession session) {
    boolean isSuccess = registrationService.veryfyOtp(username, otp);
    if (isSuccess && role.equalsIgnoreCase("user")) {
      return "redirect:/user-home?username=" + username;
    } else if(isSuccess && role.equalsIgnoreCase("admin")) {
      return "redirect:/user-all";
    }
    return "redirect:/otp";
  }

    @GetMapping("/generate-otp")
    public String generateOtp(@RequestParam("username") String username) {
    registrationService.generateOtp(username);
    return "redirect:/otp";
  }

  @GetMapping("/user-home")
  public String showUserHome(Model model, @RequestParam String username) {
    // Add any necessary model attributes
    model.addAttribute("username", username);

    // Redirect to the user home template
    return "user-home";
  }
}
