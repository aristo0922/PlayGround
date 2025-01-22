package com.villain.play.ground.PlayGround.party;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ManagePartyTest {

  static String PLATFORM = "sound wave";
  static String ALBUM = "Live and Fall";
  static long LEADER_ID = 1L;
  static long RECRUIT_ID = 1L;
  static int MAXIMUM = 6;

  @InjectMocks
  private PartyService service;

  @Mock
  private PartyRepository partyRepository;

//  @BeforeEach
//  void set_up(){
//    partyRepository
//  }


  @DisplayName("파티를 생성할 수 있다.")
  @Test
  void create(){
    Party party = service.create(new Party.PartyBuilder().platform(PLATFORM).album(ALBUM).leader(LEADER_ID).recruit(RECRUIT_ID).maximum(MAXIMUM).build());
    Assertions.assertEquals(PLATFORM, party.getPlatform());
    Assertions.assertEquals(ALBUM, party.getAlbum());
    Assertions.assertEquals(LEADER_ID, party.getLeader());
    Assertions.assertEquals(RECRUIT_ID, party.getRecruit());
    Assertions.assertEquals(MAXIMUM, party.getMaximum());
    Assertions.assertNotNull(party.getId());
  }

}
