package com.villain.play.ground.PlayGround.party;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class PartyRepositoryTest {

  @Autowired
  private PartyRepository partyRepository;
  private Party party;

  @BeforeEach
  void setup(){
    partyRepository.deleteAll();
  }

  @DisplayName("객체 저장 및 조회")
  @Test
  void findPartyById() {
    party = new Party.PartyBuilder().album("Hello NEW World").leader(0L).maximum(6)
        .recruit(0L).platform("# form").build();
    System.out.println("party = " + party);
    Party savedParty = partyRepository.save(party);
    Assertions.assertNotNull(savedParty);
    Assertions.assertEquals(savedParty.getPlatform(), party.getPlatform());


    Party foundParty = partyRepository.findPartyById(savedParty.getId());
    Assertions.assertNotNull(foundParty);
    Assertions.assertEquals(savedParty.getId(), foundParty.getId());
    Assertions.assertEquals(savedParty.getPlatform(), foundParty.getPlatform());
    Assertions.assertEquals(savedParty.getAlbum(), foundParty.getAlbum());
    Assertions.assertEquals(savedParty.getLeader(), foundParty.getLeader());
    Assertions.assertEquals(savedParty.getMaximum(), foundParty.getMaximum());
    Assertions.assertEquals(savedParty.getRecruit(), foundParty.getRecruit());
  }
}