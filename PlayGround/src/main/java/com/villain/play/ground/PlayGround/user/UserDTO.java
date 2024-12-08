package com.villain.play.ground.PlayGround.user;

public class UserDTO {

  private enum Status{
    ACTIVE, INACTIVE,WARNING
  }

  private String name;
  private String email;
  private String password;
  private String address;
  private long partyCount;
  private long leaderCount;

  private Status status;
}
