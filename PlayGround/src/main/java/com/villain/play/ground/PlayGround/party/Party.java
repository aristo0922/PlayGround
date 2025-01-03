package com.villain.play.ground.PlayGround.party;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Party {

  private int id;
  private String platform;
  private String album;
  private String leader;
  private int recruit;
  private int limit;
  private int now;

//  private LocalDateTime start;
//  private LocalDateTime end;

  public boolean isFull() {
    return limit <= now ? true : false;
  }
  public int getId(){
    return this.id;
  }
}
