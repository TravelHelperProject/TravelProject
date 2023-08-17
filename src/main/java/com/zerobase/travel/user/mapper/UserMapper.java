package com.zerobase.travel.user.mapper;

import com.zerobase.travel.user.dto.FindUserEmailDTO;
import com.zerobase.travel.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDTO> findUserEmail(String email);
    Optional<UserDTO> findUser(String email);
    Optional<UserDTO> findUserNickname(String nickname);
    String findUserLoginEmail(FindUserEmailDTO findUserEmailDTO);

    void signIn(UserDTO userDTO);
}
