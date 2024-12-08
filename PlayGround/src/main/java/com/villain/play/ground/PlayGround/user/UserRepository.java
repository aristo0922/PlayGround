package com.villain.play.ground.PlayGround.user;

public interface UserRepository {
  void insertUser(UserDTO user);
  void deleteUser(long userId);
  UserDTO findByEmailAndPassword(String id, String password);

  void countPartyById(long userId);
  void countLeaderById(long userId);
  void updatePartyCount();
  void updateLeaderCount();

  UserDTO findByUserId(String userId);
}
