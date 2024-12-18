package com.villain.play.ground.PlayGround.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
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

  public boolean isActive(){
    return status == Status.ACTIVE ? true : false;
  }
}
