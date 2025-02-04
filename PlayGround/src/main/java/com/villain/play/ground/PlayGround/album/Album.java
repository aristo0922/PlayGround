package com.villain.play.ground.PlayGround.album;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.villain.play.ground.PlayGround.constant.Artist;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Album {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column
  private String name;

  @Column
  @Enumerated(value = EnumType.STRING)
  private Artist artist;

  public Album(String name, Artist artist){
    this.name = name;
    this.artist = artist;
  }
}
