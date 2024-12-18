package com.villain.play.ground.PlayGround.party;

class Party {

  int limit = 6;
  int now = 6;

  public boolean isFull() {
    return limit <= now ? true : false;
  }
}
