package com.villain.play.ground.PlayGround.constant;

public enum Payment {
  PENDING(0), PAID(1), CANCELLED(2);

  private final int value;

  Payment(int value){
    this.value = value;
  }
}
