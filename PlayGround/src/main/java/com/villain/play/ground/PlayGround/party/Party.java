package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.reservation.Reservation;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

  private static List<Reservation> reservationList = new ArrayList<>();
//  private LocalDateTime start;
//  private LocalDateTime end;

  public Party(int id, String platform, String album, String leader, int recruit, int limit, int now){
    this.id = id;
    this.platform=platform;
    this.album = album;
    this.leader = leader;
    this.recruit = recruit;
    this.limit = limit;
    // todo: 파라미터 now 삭제, 및 초기화 방법 수정
  }
  public void addReservation(Reservation reservation){
    reservationList.add(reservation);
  }

  public boolean isFull() {
    return limit <= reservationList.size() ? true : false;
  }

  public boolean isReservedMember(String member){ // todo 나중에 파라미터 객체화 고려
    for (Reservation reservation: reservationList){
      if(reservation.getMember().equals(member)) return true;
    }
    return false;
  }

  public int getId(){
    return this.id;
  }

  public void deleteAllReservations(){
    reservationList = new ArrayList<>();
  }
}
