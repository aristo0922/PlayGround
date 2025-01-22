package com.villain.play.ground.PlayGround.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Reservation {

  private long partyId;
  private String member;
  private String user;
}
