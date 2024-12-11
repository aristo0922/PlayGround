package com.villain.play.ground.PlayGround.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public void register(UserDTO user){
    userRepository.insertUser(user);
  }

  public UserDTO login(String email, String password){
    UserDTO user = userRepository.findByEmailAndPassword(email, password);
    if(user.isActive()) return user;
    return null;
  }
}
