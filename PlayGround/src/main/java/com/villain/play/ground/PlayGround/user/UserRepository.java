package com.villain.play.ground.PlayGround.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  void insertUser(UserDTO user);
  void deleteUser(long userId);
  UserDTO findByEmailAndPassword(String id, String password);

  long countPartyById(long userId);
  long countLeaderById(long userId);
  void updatePartyCount(UserDTO user);
  void updateLeaderCount(UserDTO user);

  UserDTO findByUserId(long userId);
}
