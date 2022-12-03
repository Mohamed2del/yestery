package com.nasnav.yestery.service;

import com.nasnav.yestery.dto.UserDto;
import com.nasnav.yestery.entities.User;
import com.nasnav.yestery.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Long register(UserDto userDto);

    User login(UserDto userDto);

    Optional<User> findUserByUserName(String username);

    List<UserDto> findAllUsers();

    Role getUserRole(Long userId);
}
