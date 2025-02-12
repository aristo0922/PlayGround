package com.villain.play.ground.PlayGround.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public User login(String email, String password) throws IllegalArgumentException {
    User userInfo = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
    if(userInfo.checkSamePassword(password))
      return userInfo;
    throw new IllegalArgumentException("[Error] Incorrect Password.");
  }

  public void createUser(UserDTO dto) {
    userRepository.save(User.from(dto));
  }

  public User findById(long id) {
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
