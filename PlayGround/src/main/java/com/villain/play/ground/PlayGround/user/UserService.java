package com.villain.play.ground.PlayGround.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  public void register(UserDTO user){

  }
}
