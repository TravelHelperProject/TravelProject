package com.zerobase.travel.user.jwt;

import com.zerobase.travel.user.dto.UserDTO;
import com.zerobase.travel.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class JwtUserService implements UserDetailsService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findUserEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저는 존재하지 않습니다."));
    }

    private UserDetails createUserDetails(UserDTO userDTO) {
        return UserDTO.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
    }
}
