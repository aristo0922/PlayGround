package com.villain.play.ground.PlayGround.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.villain.play.ground.PlayGround.user.request.LoginRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @Test
  void register() {

  }

  @Test
  void login() throws Exception {
//    LoginRequest request = LoginRequest.builder().email("villainMusk@Xdinery.world")
//        .password("villains").build();
//
//    when(userService.login(request.getEmail(), request.getPassword()))
//        .thenReturn(new UserDTO(1L, "villainMusk@Xdinery.world", "Villain Musk", "address"));
//
//
//    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/login")
//            .accept(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk());
    // Mock된 서비스 계층 동작 정의
    LoginRequest request = LoginRequest.builder()
        .email("villainMusk@Xdinery.world")
        .password("villains")
        .build();

//    when(userService.login(request.getEmail(), request.getPassword()))
//        .thenReturn(new UserDTO(0L, "villainMusk@Xdinery.world", "Villain Musk", "address"));

    // 요청 본문 정의
    String requestBody = """
            {
                "email": "villainMusk@Xdinery.world",
                "password": "villains"
            }
        """;
    // 테스트 실행
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk());
  }
}