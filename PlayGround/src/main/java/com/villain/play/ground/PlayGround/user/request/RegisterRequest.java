package com.villain.play.ground.PlayGround.user.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterRequest {

  @NotNull(message = "이름은 필수 입력 항목입니다.")
  @Size(min = 2, max = 20, message = "이름은 2자 이상, 20자 이하로 입력해야 합니다.")
  private String name;

  @NotNull(message = "이메일은 필수 입력 항목입니다.")
  @Email(message = "올바른 이메일 형식이어야 합니다.")
  private String email;

  @NotNull(message = "비밀번호는 필수 입력 항목입니다.")
  @Size(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해야 합니다.")
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).+$",
      message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다.")
  private String password;

  @Size(max = 100, message = "주소는 100자 이하로 입력해야 합니다.")
  private String address;
}
