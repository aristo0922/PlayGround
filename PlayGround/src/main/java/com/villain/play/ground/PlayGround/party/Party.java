package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.constant.Artist;
import com.villain.play.ground.PlayGround.reservation.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  @ManyToOne
  @JoinColumn(name = "album_id")
  private Album album;
  @Column
  private Long leader;
  @Column
  private Long recruit;
  @Column
  private int maximum;

  private List<Reservation> reservationList;
  private Artist artist;

  @Builder
  public Party(String platform, Album album, Long leader, Long recruit, int maximum){
    this.platform=platform;
    this.album = album;
    this.leader = leader;
    this.recruit = recruit;
    this.maximum = maximum;
    this.artist = album.getArtist();
  }
  public void addReservation(Reservation reservation){
    reservationList.add(reservation);
  }

  public boolean isFull() {
    return maximum <= reservationList.size() ? true : false;
  }

  public boolean isReservedMember(String member){
    for (Reservation reservation: reservationList){
      if(reservation.getMember().equals(member)) return true;
    }
    return false;
  }

  public void deleteAllReservations(){
    reservationList = new ArrayList<>();
  }
}
