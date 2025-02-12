package com.villain.play.ground.PlayGround.party;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.constant.Artist;
import com.villain.play.ground.PlayGround.request.NewParty;
import java.util.Optional;
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

  private static long PARTY_ID = 1L;
  private static long INVALID_PARTY_ID = 0L;
  private static String PLATFORM = "sound wave";
  private static long LEADER_ID = 1L;
  private static long RECRUIT_ID = 1L;
  private static int MAXIMUM = 6;

  @InjectMocks
  private PartyService service;

  @Mock
  private PartyRepository partyRepository;

  private static Album album;
  private static NewParty validParty;
  private static NewParty invalidParty;
  private static Party savedParty;

  @BeforeEach
  void set_up() {
    //given
    album = new Album("Live and Fall", Artist.XDINARY_HEROES);
    validParty = new NewParty(album, PLATFORM, LEADER_ID, RECRUIT_ID, MAXIMUM);
    invalidParty = new NewParty(album, PLATFORM, LEADER_ID, RECRUIT_ID, MAXIMUM);
    invalidParty.setRecruit(null);

    savedParty = new Party.PartyBuilder().platform(PLATFORM).leader(LEADER_ID).recruit(RECRUIT_ID)
        .maximum(MAXIMUM).album(album).build();
    savedParty.setId(PARTY_ID);
  }


  @DisplayName("파티를 생성할 수 있다.")
  @Test
  void shouldCreateParty() {
    when(partyRepository.save(any())).thenReturn(savedParty);

    Party result = service.save(validParty);

    assertNotNull(result);

    assertEquals(PLATFORM, result.getPlatform());
    assertEquals(album, result.getAlbum());
    assertEquals(LEADER_ID, result.getLeader());
    assertEquals(RECRUIT_ID, result.getRecruit());
    assertEquals(MAXIMUM, result.getMaximum());
  }

  @DisplayName("빈 필드 존재 시 파티를 생성할 수 없다.")
  @Test
  void shouldThrowExceptionWhenCreatingInvalidParty() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(invalidParty));
  }

  @DisplayName("생성한 파티를 조회할 수 있다.")
  @Test
  void shouldRetrieveCreateParty() {
    //given
    when(partyRepository.findPartyById(PARTY_ID)).thenReturn(Optional.of(savedParty));

    //when
    Party result = service.getParty(PARTY_ID);

    // then
    assertNotNull(result);
    assertEquals(savedParty, result);
  }


  @DisplayName("존재하지 않는 파티는 조회할 수 없다..")
  @Test
  void cannot_read() {
    // given
    when(partyRepository.findPartyById(INVALID_PARTY_ID)).thenReturn(null);

    //when & then
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> service.getParty(INVALID_PARTY_ID));
  }
}
