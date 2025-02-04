package com.villain.play.ground.PlayGround.album;

import com.villain.play.ground.PlayGround.constant.Artist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AlbumTest {

  private Album album;
  @BeforeEach
  void setUp(){
    album = new Album("Live and Fall", Artist.XH);
  }

  @Test
  @DisplayName("앨범에 참여한 아티스트 정보를 얻을 수 있다.")
  void test(){
    Artist artist = album.getArtist();

    Assertions.assertEquals("XH", artist.toString());
    Assertions.assertEquals(6, artist.getMembers().size());
  }

  @Test
  @DisplayName("앨범에 참여한 아티스트인지 구분할 수 있다.")
  void test2(){
    String fakeArtist = "호두영감";
    Assertions.assertEquals(false, album.isArtist(fakeArtist));

    String trueArtist = "정수";
    Assertions.assertEquals(true, album.isArtist(trueArtist));
  }
}
