package com.villain.play.ground.PlayGround.user.request;


import lombok.Getter;

@Getter
public class RegisterRequest {


  private String name;
  private String email;
  private String password;
  private String address;

  public static boolean hasBlankFields(RegisterRequest request) {
    if (request.getEmail() == null || request.getPassword() == null
        || request.getAddress() == null) {
      return true;
    }
    return false;
  }
}
