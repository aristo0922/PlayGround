package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.reservation.Reservation;
import com.villain.play.ground.PlayGround.reservation.ReservationDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

  @OneToMany
  private List<Reservation> reservations;

  @Builder
  public Party(String platform, Album album, Long leader, Long recruit, int maximum){
    this.platform=platform;
    this.album = album;
    this.leader = leader;
    this.recruit = recruit;
    this.maximum = maximum;
  }

  public void addReservation(Reservation reservation){
    reservations.add(reservation);
  }

  public boolean isFull() {
    return maximum <= reservations.size() ? true : false;
  }

  public boolean isReservedMember(String member){
    for (Reservation reservation : reservations){
      if(reservation.getMember().equals(member)) return true;
    }
    return false;
  }

  public void deleteAllReservations(){
    this.reservations = new ArrayList<>();
  }
  public boolean isArtist(String name){
    return album.isArtist(name) || name.equals(album.getName());
  }
}
