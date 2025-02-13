package com.villain.play.ground.PlayGround.user.entity;

import com.villain.play.ground.PlayGround.constant.Status;
import com.villain.play.ground.PlayGround.party.Party;
import com.villain.play.ground.PlayGround.user.UserDTO;
import com.villain.play.ground.PlayGround.user.request.RegisterRequest;
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
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @Builder
  private User(Long id, String name, String email, String password, String address, Status status) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.address = address;
    this.status = status;
  }

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public void upgradeStatus() {
    this.status = Status.CERTIFICATED;
  }

  public void downgradeStatus() {
    this.status = Status.INACTIVE;
  }

  public boolean isSamePassword(String password) {
    if (Encryptor.isMatch(password, this.password)) {
      return true;
    }
    return false;
  }

  public boolean isActive() {
    if (this.status == Status.INACTIVE) {
      return false;
    }
    return true;
  }

  public boolean isCetificated() {
    if (this.status == Status.CERTIFICATED) {
      return true;
    }
    return false;
  }


  public static User of(UserDTO dto) {
    return User.builder()
        .id(dto.getId())
        .address(dto.getAddress())
        .email(dto.getEmail())
        .password(dto.getPassword())
        .name(dto.getName())
        .status(dto.getStatus())
        .build();
  }

  public static User of(RegisterRequest request) {
    String hashed = Encryptor.encrypt(request.getPassword());

    return User.builder().name(request.getName())
        .email(request.getEmail())
        .password(hashed)
        .status(Status.ACTIVE)
        .address(request.getAddress())
        .build();
  }

  public static User of(UserDTO dto, String password) {
    return new User(dto.getId(), dto.getName(), dto.getEmail(), password, dto.getAddress(),
        dto.getStatus(), dto.getPartyCount(), dto.getLeaderCount(), dto.getPartyList());
  }
}
