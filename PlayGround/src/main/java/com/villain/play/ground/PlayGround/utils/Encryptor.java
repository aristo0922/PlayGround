package com.villain.play.ground.PlayGround.utils;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Encryptor implements EncryptHelper{

  @Override
  public String encrypt(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  @Override
  public boolean isMatch(String password, String hashed) {
    return BCrypt.checkpw(password, hashed);
  }
}
