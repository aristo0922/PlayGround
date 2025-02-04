package com.villain.play.ground.PlayGround.reservation;

import com.villain.play.ground.PlayGround.party.Party;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ReservationDAO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "party_id")
  private Party partyId;

  @Column
  private String member;

  @Column
  private String user;
}
