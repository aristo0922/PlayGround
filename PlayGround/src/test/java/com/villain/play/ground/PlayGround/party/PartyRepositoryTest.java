package com.villain.play.ground.PlayGround.party;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

  @Test
  void findPartyById() {
    party = new Party.PartyBuilder().album("Hello NEW World").leader(0L).maximum(6)
        .recruit(0L).platform("# form").build();
    System.out.println("party = " + party);
    Party savedParty = partyRepository.save(party);
    Assertions.assertNotNull(savedParty);
    Assertions.assertEquals(savedParty.getPlatform(), party.getPlatform());



//    Party foundParty = partyRepository.findPartyById(savedParty.getId());
//    Assertions.assertNotNull(foundParty);
//    Assertions.assertEquals(party.getPlatform(), foundParty.getPlatform());
  }
}