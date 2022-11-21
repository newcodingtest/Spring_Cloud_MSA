package com.user.userservice.service;

import com.user.userservice.dto.UserDto;
import com.user.userservice.entity.UserEntity;
import com.user.userservice.vo.ResponseUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    List<ResponseUser> getUserByAll();

    UserDto getUserDetailsByEmail(String userName);
}
