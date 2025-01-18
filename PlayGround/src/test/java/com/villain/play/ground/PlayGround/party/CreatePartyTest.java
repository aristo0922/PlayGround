package com.villain.play.ground.PlayGround.party;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreatePartyTest {

  List<Party> listToCreate = new ArrayList<>();

  @InjectMocks
  PartyService partyService;

  @Mock
  private PartyRepository partyRepository;

  Party party1 = new Party.PartyBuilder().platform("JYP shop").album("HELLO WORLD").recruit(0L)
      .maximum(6).build();
  Party party2 = new Party.PartyBuilder().platform("SoundWave").album("HELLO WORLD")
      .recruit(0L).maximum(6).build();
  Party party3 = new Party.PartyBuilder().platform("MyMusic").album("HELLO WORLD").recruit(0L)
      .maximum(6).build();
  Party party4 = new Party.PartyBuilder().platform("Hanter").album("HELLO WORLD").recruit(0L)
      .maximum(6).build();
  Party party5 = new Party.PartyBuilder().platform("Spotify").album("HELLO WORLD").recruit(0L)
      .maximum(6).build();
  Party party6 = new Party.PartyBuilder().platform("Melon").album("HELLO WORLD").recruit(0L)
      .maximum(6).build();

  @BeforeEach
  void setUp() {
    listToCreate.add(party1);
    listToCreate.add(party2);
    listToCreate.add(party3);
    listToCreate.add(party4);
    listToCreate.add(party5);
    listToCreate.add(party6);
  }
}
