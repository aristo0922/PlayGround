package com.villain.play.ground.PlayGround.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.constant.Artist;
import com.villain.play.ground.PlayGround.party.Party;
import com.villain.play.ground.PlayGround.party.PartyRepository;
import com.villain.play.ground.PlayGround.reservation.Reservation;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest {

  @Mock
  PartyRepository partyRepository;

  @InjectMocks
  UserService userService;

  @Autowired
  UserRepository userRepository;

  private static Album album;
  private static final String ALBUM_NAME = "Live and Fall";
  private static final String VALID_USER_NAME = "musk";
  private static final String VALID_USER_EMAIL = "musk@example.com";


  @BeforeEach
  void setUp(){
    album = new Album(ALBUM_NAME, Artist.XDINARY_HEROES);
  }



  @DisplayName("사용자를 생성할 수 있다.")
  @Test
  void createUser() {
    // Given
    User user = new User(VALID_USER_NAME, VALID_USER_EMAIL);

    // When
    when(userRepository.save(any(User.class))).thenReturn(null);
    User savedUser = userService.createUser(user);

    // Then
    Assertions.assertNotNull(savedUser);
    Assertions.assertEquals("musk", savedUser.getName());
  }

  @DisplayName("사용자는 본인이 생성한 파티의 리더여야 한다.")
  @Test
  void userIsLeaderOfCreatedParty() {
    // Given
    User leader = new User(VALID_USER_NAME, VALID_USER_EMAIL);
    Party party = new Party(1L, "Sound Wave", album, leader.getId(), 1L, 6, new ArrayList<>());

    // When
    when(partyRepository.findById(any(Long.class))).thenReturn(Optional.of(party));

    // Then
    Assertions.assertEquals(leader.getId(), party.getLeader());
  }

  @DisplayName("사용자는 파티에 참가할 수 있다.")
  @Test
  void userCanJoinParty() {
    // Given
    User user = new User(VALID_USER_NAME, VALID_USER_EMAIL);
    Party party = new Party(1L, "Sound Wave", album, 1L, 1L, 6, new ArrayList<>());
    Reservation reservation = new Reservation(party, "정수", user.getName());

    // When
    when(partyRepository.findById(any(Long.class))).thenReturn(Optional.of(party));
    party.addReservation(reservation);

    // Then
    Assertions.assertTrue(party.getReservations().contains(reservation));
  }
}
