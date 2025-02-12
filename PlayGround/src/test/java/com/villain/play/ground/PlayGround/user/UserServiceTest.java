package com.villain.play.ground.PlayGround.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.constant.Status;
import com.villain.play.ground.PlayGround.party.PartyRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  PartyRepository partyRepository;


  @Mock
  UserRepository userRepository;
  @InjectMocks
  UserService userService;

  private static final String VALID_USER_NAME = "musk";
  private static final String VALID_USER_EMAIL = "musk@example.com";
  private static final String VALID_USER_PASSWORD = "password";
  private static final String VALID_USER_ADDRESS = "서울시 강동구 성내동 올림픽수영장 입구 맞은편";

  private static UserDTO validUserDto;
  private static User activeUser;

  private static UserDTO warningUserDTO;
  private static User warningUser;


  @BeforeEach
  void setUp() {
    // Given
    validUserDto = UserDTO.builder().name(VALID_USER_NAME)
        .email(VALID_USER_EMAIL)
        .password(VALID_USER_PASSWORD)
        .address(VALID_USER_ADDRESS)
        .partyCount(5L)
        .leaderCount(0L)
        .status(Status.ACTIVE)
        .partyList(new ArrayList<>()).build();
    activeUser = User.from(validUserDto);

    warningUserDTO = UserDTO.builder().name(VALID_USER_NAME)
        .email(VALID_USER_EMAIL)
        .password(VALID_USER_PASSWORD)
        .address(VALID_USER_ADDRESS)
        .partyCount(5L)
        .leaderCount(0L)
        .status(Status.WARNING)
        .partyList(new ArrayList<>()).build();
    warningUser = User.from(warningUserDTO);
  }


  @DisplayName("사용자를 생성할 수 있다.")
  @Test
  void createUser() {
    // When
    userService.createUser(validUserDto);

    // Then
    verify(userRepository, times(1)).save(any(User.class));
  }

  @DisplayName("사용자의 현재 인증 상태를 확인할 수 있다.")
  @Test
  void verifyUserStatus(){
    when(userRepository.findByUserId(anyLong())).thenReturn(Optional.of(activeUser));
    User foundUser = userService.getUserById(1L);
    Assertions.assertEquals(Status.ACTIVE, foundUser.getStatus());
  }

  @DisplayName("파티 참여 횟수가 기준 이상이면 CERTIFICATED 로 등급 상승.")
  @Test
  void upgradeUserToCertificated(){
    when(userRepository.findByUserId(anyLong())).thenReturn(Optional.of(activeUser));
    userService.checkAndUpgradeStatus(3L);

    Assertions.assertEquals(Status.CERTIFICATED, activeUser.getStatus());
  }

  @DisplayName("사용자의 Status 를 Downgrade 할 수 있다.")
  @Test
  void downgradeUser(){
    when(userRepository.findByUserId(anyLong())).thenReturn(Optional.of(activeUser));
    userService.checkAndDownGradeStatus(5L);
    Assertions.assertEquals(Status.WARNING, activeUser.getStatus());

    when(userRepository.findByUserId(anyLong())).thenReturn(Optional.of(warningUser));
    userService.checkAndDownGradeStatus(7L);
    Assertions.assertEquals(Status.INACTIVE, warningUser.getStatus());
  }
}
