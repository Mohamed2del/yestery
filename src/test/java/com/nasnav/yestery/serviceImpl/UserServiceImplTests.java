package com.nasnav.yestery.serviceImpl;

import com.nasnav.yestery.dto.UserDto;
import com.nasnav.yestery.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTests {

	@Autowired
	UserService userService;


	@Test
	void registerTest() {
		UserDto userDto = new UserDto(null,"testUser","testUserLastName","usernameTest","testUserPassword",null);
		Assert.assertNotNull(userService.register(userDto));
	}

	@Test
	void loginTest() {
		UserDto userDto = new UserDto(null,null,null,"admin","admin123",null);
		Assert.assertNotNull(userService.login(userDto));
	}

}
