package com.villain.play.ground.PlayGround.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

//  public void register(UserDTO user){
//    userRepository.insertUser(user);
//  }

  public UserDTO login(String email, String password){
//    UserDTO user = userRepository.findByEmailAndPassword(email, password);
//    if(user.isActive()) return user;
    return null;
  }

  public void createUser(UserDTO dto) {
    userRepository.save(User.from(dto));
  }

  public User getUserById(long id) {
    return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
  }

  public void checkAndUpgradeStatus(long id) {
    User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    if(canUpgrade(user))
      user.upgradeStatus();
  }

  private boolean canUpgrade(User user){
    if(user.getPartyCount() >= 0){
      return true;
    }
    return false;
  }

  public void checkAndDownGradeStatus(long id) {
    User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    if(shouldDowngrade(user)){
      user.downgradeStatus();
    }
  }


  private boolean shouldDowngrade(User user){
    if(user.getPartyCount() >= 0){
      return true;
    }
    return false;
  }
}
