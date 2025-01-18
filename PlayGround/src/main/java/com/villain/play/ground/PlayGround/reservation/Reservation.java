package com.villain.play.ground.PlayGround.reservation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class Reservation {

  private String member;
  private String user;

  @ToString
  @Setter
  public static class Builder{
    String member;
    String user;

    public Builder member(String member){
      this.member = member;
      return this;
    }

    public Builder user(String user) {
      this.user = user;
      return this;
    }

    public Reservation build(){
        return new Reservation(this);
    }
  }

  private Reservation(Builder builder){
    if(builder.member == null|| builder.user == null)
      throw new IllegalArgumentException();
    this.member = builder.member;
    this.user = builder.user;
  }
}
