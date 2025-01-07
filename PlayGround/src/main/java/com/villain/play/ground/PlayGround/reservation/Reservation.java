package com.villain.play.ground.PlayGround.reservation;

public class Reservation {

  private String member;
  private String user;

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
    this.member = builder.member;
    this.user = builder.user;
  }
}
