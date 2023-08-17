package com.zerobase.travel.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInDTO {
    private String email;
    private String password;
    private String passwordConfirm;
    private String name;
    private String phoneNum;
    private String nickname;
}
