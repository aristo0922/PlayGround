package com.villain.play.ground.PlayGround.user;

import com.villain.play.ground.PlayGround.user.request.LoginRequest;
import com.villain.play.ground.PlayGround.user.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.MEMBER_API_URI)
public class UserController {

  public static final String MEMBER_API_URI = "/api/members";
  private final UserService userService;

  @GetMapping("/hello")
  public String hello(){
    return "hello";
  }

  @PostMapping("/register")
  public void register(@RequestBody RegisterRequest request) {
    if (!RegisterRequest.hasBlankFields(request)) {
      UserDTO user = UserDTO.builder()
          .email(request.getEmail())
          .password(request.getPassword())
          .address(request.getAddress())
          .name(request.getName()).build();
//      userService.register(user);
    }
  }

  @PostMapping("/login")
  public void login(@RequestBody LoginRequest user) {
    if (LoginRequest.hasBlankFields(user)) {
      throw new IllegalArgumentException("입력값에 빈 필드가 존재합니다.");
    }
    UserDTO authUser = userService.login(user.getEmail(), user.getPassword());
  }
}
