package com.villain.play.ground.PlayGround.album;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.villain.play.ground.PlayGround.constant.Artist;
import com.villain.play.ground.PlayGround.party.Party;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Album {

  @Id @GeneratedValue(strategy = IDENTITY)
  @Column(name = "album_id")
  private long id;

  @Column
  private String name;

  @Column
  @Enumerated(value = EnumType.STRING)
  private Artist artist;

  @JoinColumn(name = "party_id")
  private List<Party> partyList;

  public Album(String name, Artist artist) {
    this.name = name;
    this.artist = artist;
    this.partyList = new ArrayList<>();
  }
  public Album(String name, Artist artist, List<Party> list) {
    this.name = name;
    this.artist = artist;
    this.partyList = list;
  }

  public boolean isArtist(String name) {
    return artist.isIdentifiedMember(name);
  }

  public Artist getArtist() {
    return this.artist;
  }

  public String getName() {
    return this.name;
  }

  public List<String> getMembers() {
    return this.artist.getMembers();
  }

  public void setId(long id) {
    this.id = id;
  }

  public static Album from(AlbumDto dto){
    Artist artist = Artist.valueOf(dto.getArtist());
    return new Album(dto.getAlbumName(), artist, dto.getPartyList());
  }
}
