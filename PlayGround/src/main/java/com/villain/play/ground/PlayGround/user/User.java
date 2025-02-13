package com.villain.play.ground.PlayGround.user;

import com.villain.play.ground.PlayGround.constant.Status;
import com.villain.play.ground.PlayGround.party.Party;
import com.villain.play.ground.PlayGround.utils.Encryptor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
  @Enumerated(EnumType.STRING)
  private Status status;
  private Long partyCount;
  private Long leaderCount;

  @OneToMany(mappedBy = "leader", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Party> partyLists;

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public static User from(UserDTO dto){
    return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword(), dto.getAddress(), dto.getStatus(), dto.getPartyCount(), dto.getLeaderCount(), dto.getPartyList());
  }

  public static User of(UserDTO dto, String password){
    return new User(dto.getId(), dto.getName(), dto.getEmail(), password, dto.getAddress(), dto.getStatus(), dto.getPartyCount(), dto.getLeaderCount(), dto.getPartyList());
  }

  public void upgradeStatus(){
    this.status = Status.CERTIFICATED;
  }

  public void downgradeStatus() {
    this.status = Status.INACTIVE;
  }

  public boolean checkSamePassword(String password) {
    if(this.status == Status.INACTIVE) throw new IllegalArgumentException("[Error] Access Denied account.");
    if(Encryptor.isMatch(password, this.password)) return true;
    return false;
  }

  public boolean isActive(){
    if(this.status == Status.INACTIVE) return false;
    return true;
  }
}
