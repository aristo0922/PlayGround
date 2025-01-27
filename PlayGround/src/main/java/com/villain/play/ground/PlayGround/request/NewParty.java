package com.villain.play.ground.PlayGround.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
public class NewParty {

  private String album;
  private String platform;
  private Long leader;
  private Long recruit;
  private int maximum;

  public boolean hasNullField(){
    return album.equals("")|| platform.equals("")||
        leader==null || recruit == null ;
  }
}
