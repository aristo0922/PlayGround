package com.villain.play.ground.PlayGround.constant;

import java.util.Arrays;
import java.util.List;

public enum Artist {
  XH(Arrays.asList("건일", "정수", "가온", "오드", "형준", "주연"));
  private List<String> members;

  Artist(List<String> members){
    this.members = members;
  }

  public boolean isIdentifiedMember(String name){
    for(String member: members){
      if(member.equals(name)) return true;
    }
    return false;
  }

  public List<String> getMembers(){
    return this.members;
  }
}
