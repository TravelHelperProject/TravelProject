package com.zerobase.travel.user.mapper;

import com.zerobase.travel.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDTO> findUserEmail(String email);
    Optional<UserDTO> findUser(String email);
    Optional<UserDTO> findUserNickname(String nickname);
    String findUserLoginEmail(String name, String phoneNum);
    void updatePassword(String email, String password);

    void signIn(UserDTO userDTO);
}
