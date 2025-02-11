package com.villain.play.ground.PlayGround.party;

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

  long ID = 1l;
  String ALBUM_NAME="Dead Lock";
  String ARTIST="XDINARY_HEROES";

  AlbumDto dto = new AlbumDto(ALBUM_NAME, ARTIST, new ArrayList<>());


  @DisplayName("앨범 정보 저장")
  @Test
  void saveAlbum(){
    albumService.save(dto);

    verify(albumRepository, times(1)).save(any(Album.class));
  }

  @DisplayName("아이디로 앨범 조회")
  @Test
  void getAlbumByIdTest(){
    Album expect = Album.from(dto);
    expect.setId(ID);
    when(albumRepository.findById(ID)).thenReturn(Optional.of(expect));

    Album result = albumService.getAlbumById(ID);
    Assertions.assertEquals(expect, result);
  }

  @DisplayName("존재하지 않는 아이디 조회 시 에러")
  @Test
  void nonExistId(){
    when(albumRepository.findById(any())).thenReturn(Optional.empty());
    Assertions.assertThrows(IllegalArgumentException.class, () -> albumService.getAlbumById(ID));
  }


  @Spy
  List<Album> validatorList = new ArrayList<>();

  @DisplayName("앨범 명으로 조회")
  @Test
  void searchByAlbum(){
    Album result = Album.from(dto);
    result.setId(ID);
    validatorList.add(result);

    when(albumRepository.findByName(ALBUM_NAME)).thenReturn(validatorList);
    List<Album> list = albumService.getAlbumByName(ALBUM_NAME);

    for(int i = 0; i < validatorList.size(); i++){
      Assertions.assertEquals(validatorList.get(i), list.get(i));
    }
  }

  @DisplayName("아티스트 명으로 앨범 조회")
  @Test
  void searchByArtist(){
    String artist = ARTIST;
    Album result = Album.from(dto);
    validatorList.add(result);

    when(albumRepository.findByArtist(any(Artist.class))).thenReturn(validatorList);
    List<Album> list = albumService.getAlbumByArtist(artist);

    for(int i = 0; i < validatorList.size(); i++){
      Assertions.assertEquals(validatorList.get(i), list.get(i));
    }
  }
}
