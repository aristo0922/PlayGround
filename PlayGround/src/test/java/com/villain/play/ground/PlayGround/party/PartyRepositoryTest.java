package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.album.AlbumRepository;
import com.villain.play.ground.PlayGround.constant.Artist;
import com.villain.play.ground.PlayGround.constant.Status;
import com.villain.play.ground.PlayGround.user.User;
import com.villain.play.ground.PlayGround.user.UserDTO;
import com.villain.play.ground.PlayGround.user.UserRepository;
import java.util.ArrayList;
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

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class PartyRepositoryTest {

  @Autowired
  private PartyRepository partyRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AlbumRepository albumRepository;

  private static Album album;
  private static UserDTO userLeaderDto;
  private static User userLeader;

  private static final String VALID_USER_NAME = "musk";
  private static final String VALID_USER_EMAIL = "musk@example.com";
  private static final String VALID_USER_PASSWORD = "password";
  private static final String VALID_USER_ADDRESS = "서울시 강동구 성내동 올림픽수영장 입구 맞은편";
  @BeforeEach
  void setup() {
    userLeaderDto = UserDTO.builder().name(VALID_USER_NAME)
        .email(VALID_USER_EMAIL)
        .password(VALID_USER_PASSWORD)
        .address(VALID_USER_ADDRESS)
        .partyCount(5L)
        .leaderCount(0L)
        .status(Status.ACTIVE)
        .partyList(new ArrayList<>()).build();
    userLeader = User.from(userLeaderDto);
    userLeader = userRepository.save(userLeader);
    album = new Album("Live and Fall", Artist.XDINARY_HEROES);
    album = albumRepository.save(album);
  }

  @DisplayName("객체 저장 및 조회")
  @Test
  void shouldSaveAndFindPartyById() {
    // given
    Party party = new Party.PartyBuilder()
        .album(album)
        .leader(userLeader)
        .maximum(6)
        .recruit(0L)
        .platform("# form")
        .build();

    // when
    Party savedParty = partyRepository.save(party);
    Party foundParty = partyRepository.findPartyById(savedParty.getId()).orElseThrow(IllegalArgumentException::new);

    // then
    assertNotNull(savedParty);
    assertNotNull(foundParty);
    assertEquals(savedParty.getId(), foundParty.getId());
    assertEquals(savedParty.getPlatform(), foundParty.getPlatform());
    assertEquals(savedParty.getAlbum(), foundParty.getAlbum());
    assertEquals(savedParty.getLeader(), foundParty.getLeader());
    assertEquals(savedParty.getMaximum(), foundParty.getMaximum());
    assertEquals(savedParty.getRecruit(), foundParty.getRecruit());
  }
}
