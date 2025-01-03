package com.villain.play.ground.PlayGround.party;

public class Reservation {

  private String member;
  private String user;
  private String party;
  private boolean isDeposit;

  public static class Builder{
    String member;
    String user;
    String party;
    boolean isDeposit;

    public Builder member(String member){
      this.member = member;
      return this;
    }

    public Builder user(String user){
      this.user = user;
      return this;
    }

    public Builder isDeposit(boolean isDeposit){
      this.isDeposit = isDeposit;
      return this;
    }

    public Builder party(String party){
      this.party = party;
      return this;
    }
    public Reservation build(){
        return new Reservation(this);
    }
  }

  private Reservation(Builder builder){
    this.member = builder.member;
    this.user = builder.user;
    this.party = builder.party;
    this.isDeposit = builder.isDeposit;
  }
}
