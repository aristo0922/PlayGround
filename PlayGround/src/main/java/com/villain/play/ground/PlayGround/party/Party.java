package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.reservation.Reservation;
import com.villain.play.ground.PlayGround.reservation.ReservationDTO;
import com.villain.play.ground.PlayGround.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Builder
@AllArgsConstructor
@Table(name = "party")
public class Party {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "party_id")
  private Long id;
  @Column
  private String platform;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "album_id")
  private Album album;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User leader;
  @Column
  private Long recruit;
  @Column
  private int maximum;


  @OneToMany(mappedBy = "party")
  private List<Reservation> reservations;


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
    return album.isArtist(name);
  }
}
