package com.villain.play.ground.PlayGround.user;

public interface UserRepository {
  void insertUser(UserDTO user);
  void deleteUser(long userId);
  UserDTO findByEmailAndPassword(String id, String password);

  long countPartyById(long userId);
  long countLeaderById(long userId);
  void updatePartyCount(UserDTO user);
  void updateLeaderCount(UserDTO user);

  UserDTO findByUserId(long userId);
}
