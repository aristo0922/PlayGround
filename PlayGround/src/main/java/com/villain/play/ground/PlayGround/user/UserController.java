package com.villain.play.ground.PlayGround.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.MEMBER_API_URI)
public class UserController {
  public static final String MEMBER_API_URI = "/api/members";
  @Autowired
  private final UserService userService;

  @PostMapping("/register")
  public void register(@RequestBody UserDTO user){
    userService.register(user);
  }

  @PostMapping("login")
  public void login(@RequestBody LoginRequest user){

  }

  private class LoginRequest{
    private String id;
    private String password;
  }
}
