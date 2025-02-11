package com.villain.play.ground.PlayGround.album;

import com.villain.play.ground.PlayGround.constant.Artist;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

  List<Album> findByName(String name);

  List<Album> findByArtist(Artist artist);
}
