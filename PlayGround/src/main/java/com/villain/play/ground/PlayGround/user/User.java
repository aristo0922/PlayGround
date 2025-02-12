package com.villain.play.ground.PlayGround.user;

import com.villain.play.ground.PlayGround.constant.Status;
import com.villain.play.ground.PlayGround.party.Party;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;
  private String name;
  private String email;
  private String password;
  private String address;
  private Status status;
  private Long partyCount;
  private Long leaderCount;

  @OneToMany(mappedBy = "leader")
  private List<Party> partyLists;

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public static User from(UserDTO dto){
    Status status = dto.getStatus();
    if(dto.getStatus() == null){
      throw new IllegalStateException("[ERROR] 사용자 상태가 불분명합니다. 다시 확인해주세요.");
    }
    return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword(), dto.getAddress(), status, dto.getPartyCount(), dto.getLeaderCount(), dto.getPartyList());
  }

  public void upgradeStatus(){
    this.status = Status.CERTIFICATED;
  }

  public void downgradeStatus() {
    this.status = Status.INACTIVE;
  }
}
