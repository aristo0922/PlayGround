package com.villain.play.ground.PlayGround.reservation;

import com.villain.play.ground.PlayGround.party.Party;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Entity
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "party_id")
  private Party party;

  @Column
  private String member;

  @Column
  private String user;

  public String getMember() {
    return member;
  }
  public String getUser() { return user; }
  public Long getParty(){ return this.party.getId(); }

  public Reservation(Party party, String member, String user) {
    this.party = party;
    this.member = member;
    this.user = user;
  }
}
