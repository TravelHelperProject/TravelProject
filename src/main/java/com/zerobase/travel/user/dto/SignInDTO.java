package com.zerobase.travel.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignInDTO {
    private String email;
    private String password;
    private String passwordConfirm;
    private String name;
    private String phoneNum;
    private String nickname;
}
