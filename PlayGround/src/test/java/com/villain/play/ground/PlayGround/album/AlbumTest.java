package com.villain.play.ground.PlayGround.album;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.villain.play.ground.PlayGround.constant.Artist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AlbumTest {

  private Album album;
  private final String ARTIST="XDINARY_HEROES";
  private final String ALBUM_NAME = "Live and Fall";

  @BeforeEach
  void setUp(){
    album = new Album(ALBUM_NAME, Artist.XDINARY_HEROES);
  }

  @Test
  @DisplayName("앨범에 참여한 아티스트 정보를 얻을 수 있다.")
  void shouldReturnArtistInfoFromAlbum(){
    // when
    Artist artist = album.getArtist();

    // then
    Assertions.assertEquals(ARTIST, artist.toString());
    Assertions.assertEquals(6, artist.getMembers().size());
  }

  @Test
  @DisplayName("앨범에 참여한 아티스트인지 구분할 수 있다.")
  void shouldCheckIfArtistParticipatedInAlbum(){
    // given
    String nonParticipatingArtist = "호두영감";
    String participatingArtist = "정수";

    // when
    boolean isNonParticipating = album.isArtist(nonParticipatingArtist);
    boolean isParticipating = album.isArtist(participatingArtist);

    //then
    assertFalse(isNonParticipating);
    assertTrue(isParticipating);
  }
}
