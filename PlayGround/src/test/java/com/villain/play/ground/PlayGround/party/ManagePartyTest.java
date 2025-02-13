package com.villain.play.ground.PlayGround.party;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.constant.Artist;
import com.villain.play.ground.PlayGround.constant.Status;
import com.villain.play.ground.PlayGround.request.NewParty;
import com.villain.play.ground.PlayGround.user.entity.User;
import com.villain.play.ground.PlayGround.user.UserDTO;
import com.villain.play.ground.PlayGround.user.UserService;
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
  private PartyService partyService;

  @Mock
  private UserService userService;

  @Mock
  private PartyRepository partyRepository;

  private static Album album;
  private static NewParty validParty;
  private static NewParty invalidParty;
  private static Party savedParty;
  private static User userLeader;

  @BeforeEach
  void set_up() {
    //given
    userLeader = User.of(UserDTO.builder().id(LEADER_ID).status(Status.ACTIVE).build());

    album = new Album("Live and Fall", Artist.XDINARY_HEROES);
    validParty = new NewParty(album, PLATFORM, LEADER_ID, RECRUIT_ID, MAXIMUM);
    invalidParty = new NewParty(album, PLATFORM, LEADER_ID, RECRUIT_ID, MAXIMUM);
    invalidParty.setRecruit(null);

    savedParty = new Party.PartyBuilder().platform(PLATFORM).leader(null).recruit(RECRUIT_ID)
        .maximum(MAXIMUM).album(album).build();
    savedParty.setId(PARTY_ID);
  }


  @DisplayName("파티를 생성할 수 있다.")
  @Test
  void shouldCreateParty() {
    when(userService.findById(anyLong())).thenReturn(userLeader);

    partyService.save(validParty);

    verify(partyRepository, times(1)).save(any(Party.class));
  }

  @DisplayName("빈 필드 존재 시 파티를 생성할 수 없다.")
  @Test
  void shouldThrowExceptionWhenCreatingInvalidParty() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> partyService.save(invalidParty));
  }

  @DisplayName("생성한 파티를 조회할 수 있다.")
  @Test
  void shouldRetrieveCreateParty() {
    //given
    when(partyRepository.findPartyById(PARTY_ID)).thenReturn(Optional.of(savedParty));

    //when
    Party result = partyService.getParty(PARTY_ID);

    // then
    assertNotNull(result);
    assertEquals(savedParty, result);
  }


  @DisplayName("존재하지 않는 파티는 조회할 수 없다..")
  @Test
  void cannot_read() {
    // given
    when(partyRepository.findPartyById(INVALID_PARTY_ID)).thenReturn(Optional.empty());

    //when & then
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> partyService.getParty(INVALID_PARTY_ID));
  }


//  @DisplayName("사용자는 본인이 생성한 파티의 리더여야 한다.")
//  @Test
//  void userIsLeaderOfCreatedParty() {
//    // Given
//    final String FLATFORM = "Sound Wave";
//    final int MAXIMUN = 6;
//    Party party = new Party(1L, FLATFORM, album, validUser, 1L, MAXIMUN, new ArrayList<>());
//
//    // When
//    when(partyRepository.findById(any(Long.class))).thenReturn(Optional.of(party));
//
//    // Then
//    Assertions.assertEquals(validUser, party.getLeader());
//  }
//
//  @DisplayName("사용자는 파티에 참가할 수 있다.")
//  @Test
//  void userCanJoinParty() {
//    // Given
//    User user = new User(VALID_USER_NAME, VALID_USER_EMAIL);
//    Party party = new Party(1L, "Sound Wave", album, validUser, 1L, 6, new ArrayList<>());
//    Reservation reservation = new Reservation(party, Artist.XDINARY_HEROES.getMembers().get(2), user.getName());
//
//    // When
//    when(partyRepository.findById(any(Long.class))).thenReturn(Optional.of(party));
//    party.addReservation(reservation);
//
//    // Then
//    Assertions.assertTrue(party.getReservations().contains(reservation));
//  }
}
