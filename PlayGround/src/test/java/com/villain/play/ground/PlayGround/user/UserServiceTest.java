package com.villain.play.ground.PlayGround.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.constant.Status;
import com.villain.play.ground.PlayGround.user.entity.User;
import com.villain.play.ground.PlayGround.user.request.RegisterRequest;
import com.villain.play.ground.PlayGround.utils.Encryptor;
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
  UserRepository userRepository;

  @InjectMocks
  UserService userService;

  private static final String VALID_USER_NAME = "musk";
  private static final String VALID_USER_EMAIL = "musk@example.com";
  private static final String VALID_USER_PASSWORD = "password";
  private static String hashed;
  private static final String VALID_USER_ADDRESS = "서울시 강동구 성내동 올림픽수영장 입구 맞은편";

  private static UserDTO validUserDto;
  private static User activeUser;


  @BeforeEach
  void setUp() {
    hashed = Encryptor.encrypt(VALID_USER_PASSWORD);

    // Given
    validUserDto = UserDTO.builder().name(VALID_USER_NAME)
        .email(VALID_USER_EMAIL)
        .password(hashed)
        .address(VALID_USER_ADDRESS)
        .partyCount(5L)
        .leaderCount(0L)
        .status(Status.ACTIVE)
        .partyList(new ArrayList<>()).build();
    activeUser = User.of(validUserDto);
  }


  @DisplayName("회원가입이 정상적으로 이뤄진다.")
  @Test
  void createUser() {
    // given
    RegisterRequest request = RegisterRequest.builder().name(VALID_USER_NAME)
        .email(VALID_USER_EMAIL)
        .password(VALID_USER_PASSWORD)
        .address(VALID_USER_ADDRESS).build();
    // When
    userService.createUser(request);

    // Then
    verify(userRepository, times(1)).save(any(User.class));
  }

  @DisplayName("사용자의 현재 인증 상태를 확인할 수 있다.")
  @Test
  void verifyUserStatus() {
    when(userRepository.findById(anyLong())).thenReturn(Optional.of(activeUser));
    User foundUser = userService.findById(1L);
    Assertions.assertTrue(foundUser.isActive());
  }

  @DisplayName("파티 참여 횟수가 기준 이상이면 CERTIFICATED 로 등급 상승.")
  @Test
  void upgradeUserToCertificated() {
    User mockUser = mock(User.class);
    long userId = 1L;
    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

    userService.checkAndUpgradeStatus(userId);

    verify(mockUser).upgradeStatus();
  }

  @DisplayName("사용자의 Status 를 Downgrade 할 수 있다.")
  @Test
  void downgradeUser() {
    User mockUser = mock(User.class);
    long userId = 1L;
    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
    userService.checkAndDownGradeStatus(userId);
    verify(mockUser).downgradeStatus();
  }

  @DisplayName("이메일과 비밀번호가 일치하면 로그인이 성공한다..")
  @Test
  void loginSuccess() throws IllegalArgumentException {
    when(userRepository.findByEmail(VALID_USER_EMAIL)).thenReturn(Optional.of(activeUser));
    User mockUser = mock(User.class);
    when(userRepository.findByEmail(VALID_USER_EMAIL)).thenReturn(Optional.ofNullable(mockUser));
    when(mockUser.isSamePassword(VALID_USER_PASSWORD)).thenReturn(true);
    when(mockUser.isActive()).thenReturn(true);
    User loginedUser = userService.login(VALID_USER_EMAIL, VALID_USER_PASSWORD);

    Assertions.assertNotNull(loginedUser);
  }

  @DisplayName("잘못된 비밀번호로 로그인할 경우 예외가 발생한다.")
  @Test
  void loginFailDueToWrongPassword() {
    User mockUser = mock(User.class);
    when(userRepository.findByEmail(VALID_USER_EMAIL)).thenReturn(Optional.ofNullable(mockUser));
    when(mockUser.isSamePassword(anyString())).thenReturn(false);
    when(mockUser.isActive()).thenReturn(true);

    Assertions.assertThrows(IllegalArgumentException.class,
        () -> userService.login(VALID_USER_EMAIL, "wrongPassword"));
  }

  @DisplayName("존재하지 않는 이메일로 로그인할 경우 예외가 발생한다.")
  @Test
  void loginFailDutoNonExistentEmail() {
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

    Assertions.assertThrows(IllegalArgumentException.class, () ->
        userService.login("unknown@example.com", VALID_USER_PASSWORD));
  }
}
