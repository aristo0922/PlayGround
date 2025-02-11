package com.villain.play.ground.PlayGround.album;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.album.AlbumDto;
import com.villain.play.ground.PlayGround.album.AlbumRepository;
import com.villain.play.ground.PlayGround.album.AlbumService;
import com.villain.play.ground.PlayGround.constant.Artist;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceTest {
  @InjectMocks
  private AlbumService albumService;
  @Mock
  private AlbumRepository albumRepository;

  private final long ID = 1l;
  private final String ALBUM_NAME="Dead Lock";
  private final String ARTIST="XDINARY_HEROES";
  private final AlbumDto dto = new AlbumDto(ALBUM_NAME, ARTIST, new ArrayList<>());


  @DisplayName("앨범 정보 저장")
  @Test
  void saveAlbum(){
    // when: 실제 실행
    albumService.save(dto);

    //then: 검증
    verify(albumRepository, times(1)).save(any(Album.class));
  }

  @DisplayName("아이디로 앨범 조회")
  @Test
  void getAlbumByIdTest(){
    // given: 테스트 준비
    Album expect = Album.from(dto);
    expect.setId(ID);
    when(albumRepository.findById(ID)).thenReturn(Optional.of(expect));

    // when
    Album result = albumService.getAlbumById(ID);

    // then
    Assertions.assertEquals(expect, result);
  }

  @DisplayName("존재하지 않는 아이디 조회 시 에러")
  @Test
  void getAlbumByNonExistingId(){
    // given
    when(albumRepository.findById(any())).thenReturn(Optional.empty());

    // when & then
    Assertions.assertThrows(IllegalArgumentException.class, () -> albumService.getAlbumById(ID));
  }

  @DisplayName("앨범 명으로 조회")
  @Test
  void searchByAlbumName(){
    // given
    Album album = Album.from(dto);
    album.setId(ID);
    List<Album> expectedList = List.of(album);
    when(albumRepository.findByName(ALBUM_NAME)).thenReturn(expectedList);

    //when
    List<Album> resultList = albumService.getAlbumByName(ALBUM_NAME);

    // then
    Assertions.assertEquals(expectedList.size(), resultList.size());
    assertIterableEquals(expectedList, resultList);
  }

  @DisplayName("아티스트 명으로 앨범 조회")
  @Test
  void searchByArtistName(){
    // given
    Album album = Album.from(dto);
    List<Album> expectedList = List.of(album);
    when(albumRepository.findByArtist(any(Artist.class))).thenReturn(expectedList);

    // when
    List<Album> resultList = albumService.getAlbumByArtist(ARTIST);

    // then
    assertEquals(expectedList.size(), resultList.size());
    assertIterableEquals(expectedList, resultList);
  }
}
