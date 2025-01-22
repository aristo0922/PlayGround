package com.villain.play.ground.PlayGround.party;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
  static Party party;

  @BeforeEach
  void set_up(){
    party = new Party.PartyBuilder().platform(PLATFORM).album(ALBUM).leader(LEADER_ID).recruit(RECRUIT_ID).maximum(MAXIMUM).build();
    party.setId(2L);
    when(partyRepository.save(any())).thenReturn(party);
  }


  @DisplayName("파티를 생성할 수 있다.")
  @Test
  void create(){
    Party result = service.save(party);

    Assertions.assertEquals(PLATFORM, result.getPlatform());
    Assertions.assertEquals(ALBUM, result.getAlbum());
    Assertions.assertEquals(LEADER_ID, result.getLeader());
    Assertions.assertEquals(RECRUIT_ID, result.getRecruit());
    Assertions.assertEquals(MAXIMUM, result.getMaximum());
  }

}
