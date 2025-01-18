package com.villain.play.ground.PlayGround.party;


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
}
