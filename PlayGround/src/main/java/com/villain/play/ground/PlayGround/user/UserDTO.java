package com.villain.play.ground.PlayGround.user;


import lombok.Data;

@Data
public class UserDTO {

  private enum Status{
    ACTIVE, INACTIVE,WARNING
  }

  private long id;
  private String name;
  private String email;
  private String password;
  private String address;
  private long partyCount;
  private long leaderCount;

  private Status status;

  public void partyCountUp(){
    this.partyCount++;
  }

  public void leaderCountUp(){
    this.leaderCount++;
  }
}
