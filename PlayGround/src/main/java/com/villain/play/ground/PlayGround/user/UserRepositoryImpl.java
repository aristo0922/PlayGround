package com.villain.play.ground.PlayGround.user;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository{
  private static Map<Long, UserDTO> store = new HashMap<>();

  @Override
  public void insertUser(UserDTO user) {
    store.put(1L, user);
  }

  @Override
  public void deleteUser(long userId) {
    store.remove(userId);
  }

  @Override
  public UserDTO findByEmailAndPassword(String email, String password) {
    Optional<UserDTO> filtered = store.values().stream()
        .filter(user -> email.equals(user.getEmail()) && password.equals(user.getPassword()))
        .findFirst();
    if(filtered.isEmpty())
      throw new NoSuchElementException();
    return filtered.get();
  }

  @Override
  public long countPartyById(long userId) {
    UserDTO user= findByUserId(userId);
    return user.getPartyCount();
  }

  @Override
  public long countLeaderById(long userId) {
    UserDTO user = findByUserId(userId);
    return user.getLeaderCount();
  }

  @Override
  public void updatePartyCount(UserDTO user) {
    UserDTO stored = findByUserId(user.getId());
    stored.partyCountUp();
  }

  @Override
  public void updateLeaderCount(UserDTO user) {
    UserDTO stored = findByUserId(user.getId());
    stored.leaderCountUp();
  }

  @Override
  public UserDTO findByUserId(long userId) {
    return store.get(userId);
  }
}
