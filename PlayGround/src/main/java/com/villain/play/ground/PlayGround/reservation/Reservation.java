package com.villain.play.ground.PlayGround.reservation;

public class Reservation {

  private String member;
  private String user;
  private int party;
  private boolean isDeposit; // 객체에서 분리시키는게 좋을 것 같은데

  public int getParty() {
    return party;
  }

  public static class Builder{
    String member;
    String user;
    int party;
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

    public Builder party(int party){
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
