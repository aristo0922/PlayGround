package com.villain.play.ground.PlayGround.user.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {

  private final String email;
  private final String password;

  public static boolean hasBlankFields(LoginRequest request) {
    if (request.getEmail() == null || request.getPassword() == null) {
      return true;
    }
    return false;
  }
}
