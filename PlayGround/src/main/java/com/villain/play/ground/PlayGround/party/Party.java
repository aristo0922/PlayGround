package com.villain.play.ground.PlayGround.party;

import java.time.LocalDateTime;

public class Party {

  private int id;
  private String platform;
  private String album;
  private String leaderId;
  private String recruitId;
  private int limit = 6;
  private int now = 6;
  private LocalDateTime start;
  private LocalDateTime end;

  public boolean isFull() {
    return limit <= now ? true : false;
  }
}
