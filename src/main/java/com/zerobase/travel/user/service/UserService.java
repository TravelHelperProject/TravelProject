package com.zerobase.travel.user.service;

import com.zerobase.travel.user.jwt.JwtTokenProvider;
import com.zerobase.travel.user.dto.*;

import com.zerobase.travel.user.exception.LoginFailedException;
import com.zerobase.travel.user.exception.SignInFailedException;
import com.zerobase.travel.user.exception.UserNotFoundException;
import com.zerobase.travel.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    public boolean signIn(SignUpDTO signUpDTO) {

        if (userMapper.findUser(signUpDTO.getEmail()).isPresent()) {
            throw new SignInFailedException("중복된 이메일 입니다.");
        } else if (!signUpDTO.getEmail().contains("@")) {
            throw new SignInFailedException("이메일 형식이 아닙니다.");
        } else if (userMapper.findUserNickname(signUpDTO.getNickname()).isPresent()) {
            throw new SignInFailedException("현재 사용중인 닉네임 입니다.");
        } else if (signUpDTO.getPassword().length() < 8) {
            throw new SignInFailedException("비밀번호는 8자 이상으로 입력해주세요.");
        } else if (!signUpDTO.getPassword().equals(signUpDTO.getPasswordConfirm())) {
            throw new SignInFailedException("비밀번호가 일치하지 않습니다.");
        }

        UserDTO userSignIn = UserDTO.builder()
                .email(signUpDTO.getEmail())
                .password(passwordEncoder.encode(signUpDTO.getPassword()))
                .name(signUpDTO.getName())
                .phoneNum(signUpDTO.getPhoneNum())
                .nickname(signUpDTO.getNickname())
                .createdAt(LocalDateTime.now())
                .build();

        userMapper.signUp(userSignIn);

        return userMapper.findUserEmail(signUpDTO.getEmail()).isPresent();
    }

    //로그인 토큰발급
    public String login(LoginDTO loginDTO) {
        if(!userMapper.findUser(loginDTO.getUserEmail()).isPresent()){
            throw new LoginFailedException("잘못된 아이디 입니다.");
        }

        Optional<UserDTO> userDTO = userMapper.findUser(loginDTO.getUserEmail());

        if (!passwordEncoder.matches(loginDTO.getUserPassword(), userDTO.get().getPassword())) {
            throw new LoginFailedException("잘못된 비밀번호 입니다.");
        }

        return userDTO.get().getEmail();
    }

    public TokenDTO tokenGenerator(String userEmail) {

        String accessToken = jwtTokenProvider.createAccessToken(userEmail);
        String refreshToken = jwtTokenProvider.createRefreshToken(userEmail);

        return TokenDTO.builder()
                .accessToken("Bearer " + accessToken)
                .refreshToken("Bearer " + refreshToken)
                .build();
    }

    //아이디찾기
    public String findUserLoginEmail(FindUserEmailDTO findUserEmailDTO) {
        if (!userMapper.findUserLoginEmail(findUserEmailDTO).isEmpty()){
            return userMapper.findUserLoginEmail(findUserEmailDTO);
        } else {
            throw new UserNotFoundException("이메일을 찾을 수 없습니다.");
        }
    }

    public boolean findUserLoginPassword(String email) {
        if (!userMapper.findUser(email).isPresent()) {
            throw new UserNotFoundException("가입되지 않은 이메일 입니다.");
        }
        return true;
    }
}
