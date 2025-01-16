package com.villain.play.ground.PlayGround.party;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreatePartyTest {

  List<Party> listToCreate = new ArrayList<>();
  Party party1 = new Party.PartyBuilder().platform("JYP shop").album("HELLO WORLD").id(0).recruit(0).limit(6).build();
  Party party2 = new Party.PartyBuilder().platform("SoundWave").album("HELLO WORLD").id(0).recruit(0).limit(6).build();
  Party party3 = new Party.PartyBuilder().platform("MyMusic").album("HELLO WORLD").id(0).recruit(0).limit(6).build();
  Party party4 = new Party.PartyBuilder().platform("Hanter").album("HELLO WORLD").id(0).recruit(0).limit(6).build();
  Party party5 = new Party.PartyBuilder().platform("Spotify").album("HELLO WORLD").id(0).recruit(0).limit(6).build();
  Party party6 = new Party.PartyBuilder().platform("Melon").album("HELLO WORLD").id(0).recruit(0).limit(6).build();

  @BeforeEach
  void setUp(){
    listToCreate.add(party1);
    listToCreate.add(party2);
    listToCreate.add(party3);
    listToCreate.add(party4);
    listToCreate.add(party5);
    listToCreate.add(party6);
  }
}
