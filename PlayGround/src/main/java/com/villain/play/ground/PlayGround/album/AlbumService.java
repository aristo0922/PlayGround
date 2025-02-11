package com.villain.play.ground.PlayGround.album;

import com.villain.play.ground.PlayGround.constant.Artist;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AlbumService {

  private final AlbumRepository albumRepository;

  public void save(AlbumDto dto){
    Album album = Album.from(dto);
    albumRepository.save(album);
  }

  public Album getAlbumById(Long id){
    Optional<Album>  album = albumRepository.findById(id);

    if(album.isEmpty()){
      throw new IllegalArgumentException("[Error] no exist id.");
    }

    return album.get();
  }

  public List<Album> getAlbumByName(String name){
    List<Album> albumList = albumRepository.findByName(name);
    return albumList;
  }

  public List<Album> getAlbumByArtist(Artist artist){
    return albumRepository.findByArtist(artist);
  }

}
