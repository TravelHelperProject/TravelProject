package com.zerobase.travel.user.controller;

import com.zerobase.travel.user.dto.LoginDTO;
import com.zerobase.travel.user.dto.SignInDTO;
import com.zerobase.travel.user.dto.TokenDTO;
import com.zerobase.travel.user.dto.UserDTO;
import com.zerobase.travel.user.exception.LoginFailedException;
import com.zerobase.travel.user.exception.SignInFailedException;
import com.zerobase.travel.user.exception.UserNotFoundException;
import com.zerobase.travel.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signIn")
    public ResponseEntity join(@RequestBody SignInDTO signInDTO) {
        ResponseEntity responseEntity = null;

        try {
            userService.signIn(signInDTO);
            responseEntity = ResponseEntity.status(HttpStatus.OK)
                    .body("회원가입이 완료되었습니다.");
        } catch (SignInFailedException e) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        ResponseEntity responseEntity = null;

        try {
            String userEmail = userService.login(loginDTO);
            TokenDTO token = userService.tokenGenerator(userEmail);
            ResponseCookie responseCookie =
                    ResponseCookie.from(HttpHeaders.SET_COOKIE, token.getRefreshToken())
                            .path("/")
                            .maxAge(60 * 60 * 24 * 14) //2주
                            .httpOnly(true)
                            .build();

            responseEntity = ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                    .body("로그인 성공");
        } catch (LoginFailedException e) {
            log.debug(e.getMessage());
            responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

        return responseEntity;
    }

    @PostMapping("/logout")
    public ResponseEntity logout(
            @CookieValue(value = HttpHeaders.SET_COOKIE)Cookie refreshCookie
            ) {
        ResponseEntity responseEntity = null;
        try {
            ResponseCookie.from(HttpHeaders.SET_COOKIE, "")
                    .path("/")
                    .httpOnly(true)
                    .secure(true)
                    .maxAge(0).build();

            responseEntity = ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                    .body("로그아웃 성공");
        } catch (LoginFailedException e) {
            log.debug(e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("false");
        }
        return responseEntity;
    }

    @PostMapping("/findId")
    public ResponseEntity findUserEmail(@RequestBody UserDTO userDTO) {
        ResponseEntity responseEntity = null;

        try {
            String result = userService.findUserLoginEmail(userDTO.getName(), userDTO.getPhoneNum());
            responseEntity = ResponseEntity.status(HttpStatus.OK)
                    .body(result);
        } catch (UserNotFoundException e) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        return responseEntity;
    }
}
