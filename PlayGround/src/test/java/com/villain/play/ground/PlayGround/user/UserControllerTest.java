package com.villain.play.ground.PlayGround.user;


import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.villain.play.ground.PlayGround.user.request.LoginRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserService userService;

  @Test
  void register() {

  }

  @Test
  public void helloTest() throws Exception{
    String hello = "hello";
    mockMvc.perform(MockMvcRequestBuilders.get("/api/members/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string(hello));
  }

  @Test
  void login() throws Exception {
    LoginRequest request = LoginRequest.builder()
        .email("villainMusk@Xdinery.world")
        .password("villains")
        .build();

    String requestBody = """
            {
                "email": "villainMusk@Xdinery.world",
                "password": "villains"
            }
        """;

    mockMvc.perform(MockMvcRequestBuilders.post("/api/members/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk());

    verify(userService).login(request.getEmail(), request.getPassword());
  }

  @Test
  void login_fail() throws Exception{
    String requestBody = """
            {
                "email": "villainMusk@Xdinery.world",
            }
        """;

    mockMvc.perform(MockMvcRequestBuilders.post("/api/members/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
        .andExpect(status().isBadRequest());
  }
}