package com.villain.play.ground.PlayGround.party;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface PartyRepository extends JpaRepository<Party, Long> {

  @Query("SELECT p FROM part p WHERE U.ID == :id")
  Party findPartyById(@Param("id") Long id);

//  public static Party getPartyById(int id){
//    Party party = new Party(0, "sound cloud", "live and fall", "villain", 0, 6, 6);
//
//    return party;
//  }
}
