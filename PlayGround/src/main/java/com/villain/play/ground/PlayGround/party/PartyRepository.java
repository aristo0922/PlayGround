package com.villain.play.ground.PlayGround.party;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

  Party save(Party party);

  @Query("SELECT p FROM Party p WHERE p.id = :id")
  Party findPartyById(@Param("id") Long id);

  void deleteAll();
//  public static Party getPartyById(int id){
//    Party party = new Party(0, "sound cloud", "live and fall", "villain", 0, 6, 6);
//
//    return party;
//  }
}
