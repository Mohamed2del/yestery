package com.nasnav.yestery.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Integer roleId;
}
