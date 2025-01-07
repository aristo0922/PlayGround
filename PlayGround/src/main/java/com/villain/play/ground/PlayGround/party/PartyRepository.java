package com.villain.play.ground.PlayGround.party;


import org.springframework.stereotype.Repository;

@Repository
public class PartyRepository {

  public static Party getPartyById(int id){
    Party party = new Party(0, "sound cloud", "live and fall", "villain", 0, 6, 6);

    return party;
  }
}
