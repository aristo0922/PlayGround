package com.villain.play.ground.PlayGround.party;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.request.NewParty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ExtendWith(MockitoExtension.class)
public class ManagePartyTest {

  static long PARTY_ID=1L;
  static String PLATFORM = "sound wave";
  static String ALBUM = "Live and Fall";
  static long LEADER_ID = 1L;
  static long RECRUIT_ID = 1L;
  static int MAXIMUM = 6;

  @InjectMocks
  private PartyService service;

  @Mock
  private PartyRepository partyRepository;
  static NewParty party;
  static NewParty nonRecruit;
  static Party nomal;

  @BeforeEach
  void set_up(){
    party = new NewParty(ALBUM, PLATFORM, LEADER_ID, RECRUIT_ID, MAXIMUM);
    nonRecruit = new NewParty(ALBUM, PLATFORM, LEADER_ID, RECRUIT_ID, MAXIMUM);
    nonRecruit.setRecruit(null);
    nomal = new Party.PartyBuilder().platform(PLATFORM).leader(LEADER_ID).recruit(RECRUIT_ID).maximum(
        MAXIMUM).album(ALBUM).build();
    nomal.setId(PARTY_ID);
  }


  @DisplayName("파티를 생성할 수 있다.")
  @Test
  void create(){
    when(partyRepository.save(any())).thenReturn(nomal);

    Party result = service.save(party);

    Assertions.assertEquals(PLATFORM, result.getPlatform());
    Assertions.assertEquals(ALBUM, result.getAlbum());
    Assertions.assertEquals(LEADER_ID, result.getLeader());
    Assertions.assertEquals(RECRUIT_ID, result.getRecruit());
    Assertions.assertEquals(MAXIMUM, result.getMaximum());
  }

  @DisplayName("빈 필드 존재 시 파티를 생성할 수 없다.")
  @Test
  void cannot_create(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(nonRecruit));
  }

  @DisplayName("생성한 파티를 조회할 수 있다.")
  @Test
  void read_party(){
    when(partyRepository.findPartyById(PARTY_ID)).thenReturn(nomal);
    Party result = service.getParty(PARTY_ID);
    Assertions.assertEquals(nomal, result);
  }


}
