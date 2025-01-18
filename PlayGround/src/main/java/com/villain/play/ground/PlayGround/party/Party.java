package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.reservation.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "party")
public class Party {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String platform;
  @Column
  private String album;
  @Column
  private Long leader;
  @Column
  private Long recruit;
  @Column
  private int maximum;

  private static List<Reservation> reservationList = new ArrayList<>();
//  private LocalDateTime start;
//  private LocalDateTime end;

  @Builder
  public Party(String platform, String album, Long leader, Long recruit, int maximum, int now){
    this.platform=platform;
    this.album = album;
    this.leader = leader;
    this.recruit = recruit;
    this.maximum = maximum;
    // todo: 파라미터 now 삭제, 및 초기화 방법 수정
  }
  public void addReservation(Reservation reservation){
    reservationList.add(reservation);
  }

  public boolean isFull() {
    return maximum <= reservationList.size() ? true : false;
  }

  public boolean isReservedMember(String member){ // todo 나중에 파라미터 객체화 고려
    for (Reservation reservation: reservationList){
      if(reservation.getMember().equals(member)) return true;
    }
    return false;
  }

  public void deleteAllReservations(){
    reservationList = new ArrayList<>();
  }
}
