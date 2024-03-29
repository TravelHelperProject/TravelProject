package com.zerobase.travel.user.controller;

import com.zerobase.travel.user.dto.*;
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

    @PostMapping("/signUp")
    public ResponseEntity join(@RequestBody SignUpDTO signUpDTO) {
        ResponseEntity responseEntity = null;

        try {
            userService.signIn(signUpDTO);
            responseEntity = ResponseEntity.status(HttpStatus.OK)
                    .body("회원가입이 완료되었습니다.");
        } catch (SignInFailedException e) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            String userEmail = userService.login(loginDTO);
            TokenDTO token = userService.tokenGenerator(userEmail);
            return new ResponseEntity<TokenDTO>(token,HttpStatus.OK);

        } catch (LoginFailedException e) {
            log.debug(e.getMessage());
            TokenDTO result = new TokenDTO();
            result.setMessage(e.getMessage());
            return new ResponseEntity<TokenDTO>(result,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.debug(e.getMessage());
            TokenDTO result = new TokenDTO();
            result.setMessage(e.getMessage());
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity findUserEmail(@RequestBody FindUserEmailDTO findUserEmailDTO) {
        ResponseEntity responseEntity = null;

        try {
            String result = userService.findUserLoginEmail(findUserEmailDTO);
            responseEntity = ResponseEntity.status(HttpStatus.OK)
                    .body(result);
        } catch (UserNotFoundException e) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        return responseEntity;
    }
}
