package com.villain.play.ground.PlayGround.request;


import com.villain.play.ground.PlayGround.album.Album;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
public class NewParty {

  private Album album;
  private String platform;
  private Long leader;
  private Long recruit;
  private int maximum;

  public boolean hasNullField(){
    return album == null || platform.equals("")||
        leader==null || recruit == null ;
  }
}
