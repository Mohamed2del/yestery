package com.nasnav.yestery.controllers;

import com.nasnav.yestery.config.SecurityConfig;
import com.nasnav.yestery.dto.UserDto;
import com.nasnav.yestery.entities.User;
import com.nasnav.yestery.exceptionhandle.UserException;
import com.nasnav.yestery.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Api(  tags="User APIs", description = "Provides Register and Login Apis")

public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    SecurityConfig securityConfig;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto userDto){
         userService.register(userDto);
         return ResponseEntity.ok("Welcome to NASNAV");
    }



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto) throws Exception {
        User user = userService.login(userDto);
        if(user !=null || user.getPassword().equals(userDto.getPassword())) {
            userDto.setFirstName(user.getFirstName());
            userDto.setUserName(user.getUserName());
            userDto.setLastName(user.getLastName());
            userDto.setRoleId(user.getRole().ordinal());
            userDto.setUserId(user.getId());
            return ResponseEntity.ok(userDto);
        }
        throw new UserException("Username or Password are wrong ! please try again");
    }
}
