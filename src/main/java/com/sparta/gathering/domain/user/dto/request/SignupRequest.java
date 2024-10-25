package com.sparta.gathering.domain.user.dto.request;

import com.sparta.gathering.domain.user.enums.IdentityProvider;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

  @Email(message = "이메일 형식이 올바르지 않습니다.")
  @NotBlank(message = "이메일을 입력해주세요.")
  private String email;

  @NotBlank(message = "닉네임을 입력해주세요.")
  private String nickName;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
  private String password;

  @NotNull(message = "로그인 제공자가 올바르지 않습니다.")
  private IdentityProvider identityProvider;

}