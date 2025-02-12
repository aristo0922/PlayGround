package com.villain.play.ground.PlayGround.user;


import com.villain.play.ground.PlayGround.constant.Status;
import com.villain.play.ground.PlayGround.party.Party;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserDTO {

  private Long id;
  private String name;
  private String email;
  private String password;
  private String address;
  private long partyCount;
  private long leaderCount;

  private Status status;
  private List<Party> partyList;

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
