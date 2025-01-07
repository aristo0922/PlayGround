package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.reservation.Reservation;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Party {

  private int id;
  private String platform;
  private String album;
  private String leader;
  private int recruit;
  private int limit;
  private int now;

  private List<Reservation> reservationList;
//  private LocalDateTime start;
//  private LocalDateTime end;

  public Party(int id, String platform, String album, String leader, int recruit, int limit, int now){
    this.id = id;
    this.platform=platform;
    this.album = album;
    this.leader = leader;
    this.recruit = recruit;
    this.limit = limit;
    this.now = now;
  }

  public void addReservation(Reservation reservation){
    reservationList.add(reservation);
  }

  public boolean isFull() {
    return limit <= now ? true : false;
  }
  public int getId(){
    return this.id;
  }


}
