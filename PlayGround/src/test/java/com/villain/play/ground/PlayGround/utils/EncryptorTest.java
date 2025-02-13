package com.villain.play.ground.PlayGround.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class EncryptorTest {

  @Autowired
  private EncryptHelper encryptHelper;

  @DisplayName("password 암호화 모듈 정상 동작")
  @Test
  void verft_password(){
    String password = "VillainsPassword!23";
    String encrypted = encryptHelper.encrypt(password);
    Assertions.assertTrue(encryptHelper.isMatch(password, encrypted));
  }


  @Configuration
  static class testConfig{
    @Bean
    public EncryptHelper encryptHelper(){
      return new Encryptor();
    }
  }

}