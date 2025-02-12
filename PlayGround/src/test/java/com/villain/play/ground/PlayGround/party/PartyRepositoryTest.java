package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.album.AlbumRepository;
import com.villain.play.ground.PlayGround.constant.Artist;
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
  private AlbumRepository albumRepository;

  private Album album;

  @BeforeEach
  void setup() {
    partyRepository.deleteAll();
    album = new Album("Live and Fall", Artist.XDINARY_HEROES);
    albumRepository.save(album);
  }

  @DisplayName("객체 저장 및 조회")
  @Test
  void shouldSaveAndFindPartyById() {
    // given
    Party party = new Party.PartyBuilder()
        .album(album)
        .leader(0L)
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
