package com.villain.play.ground.PlayGround.party;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.album.AlbumDto;
import com.villain.play.ground.PlayGround.album.AlbumRepository;
import com.villain.play.ground.PlayGround.album.AlbumService;
import com.villain.play.ground.PlayGround.constant.Artist;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceTest {
  @InjectMocks
  private AlbumService albumService;
  @Mock
  private AlbumRepository albumRepository;

  @DisplayName("앨범 정보 저장")
  @Test
  void saveAlbum(){
    AlbumDto dto = new AlbumDto("Dead Lock", "XDINARY_HEROES", new ArrayList<>());

    albumService.save(dto);

    verify(albumRepository, times(1)).save(any(Album.class));
  }
}
