package com.villain.play.ground.PlayGround.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class ReservationDTO {

  private long partyId;
  private String member;
  private String user;
}
