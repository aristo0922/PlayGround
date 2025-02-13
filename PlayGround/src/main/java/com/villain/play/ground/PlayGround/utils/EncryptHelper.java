package com.villain.play.ground.PlayGround.utils;


public interface EncryptHelper {

  String encrypt(String password);
  boolean isMatch(String password, String hashed);
}
