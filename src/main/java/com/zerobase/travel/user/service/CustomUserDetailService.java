package com.zerobase.travel.user.service;

import com.zerobase.travel.user.dto.UserDTO;
import com.zerobase.travel.user.exception.UserNotFoundException;
import com.zerobase.travel.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userMapper.findUser(userEmail)
                .map(user -> addAuthorities(user))
                .orElseThrow(() -> new UserNotFoundException(userEmail + "찾을 수 없는 이메일입니다."));
    }

    private UserDTO addAuthorities(UserDTO userDTO) {
        userDTO.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDTO.getUserRole())));

        return userDTO;
    }
}
