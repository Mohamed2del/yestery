package com.nasnav.yestery.serviceImpl;

import com.nasnav.yestery.config.SecurityConfig;
import com.nasnav.yestery.dto.UserDto;
import com.nasnav.yestery.entities.User;
import com.nasnav.yestery.enums.Role;
import com.nasnav.yestery.exceptionhandle.UserException;
import com.nasnav.yestery.repository.UserRepository;
import com.nasnav.yestery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SecurityConfig securityConfig;
    @Override
    public Long register(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUserName(userDto.getUserName());
        if(userOptional.isPresent())
                throw new UserException("Username is used before , please try another one");
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setRole(Role.USER);
        // encrypt the password
          user.setPassword(securityConfig.passwordEncoder().encode(userDto.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public User login(UserDto userDto){
        String userName = userDto.getUserName();
        String password = securityConfig.passwordEncoder().encode(userDto.getPassword());
        Optional<User> userOptional = findUserByUserName(userName);
        User user = userOptional.get();
        return user;
    }
    @Override
    public Optional<User> findUserByUserName(String userName) {
        return Optional.of(userRepository.findByUserName(userName).orElseThrow());
    }

    @Override
    public List<UserDto> findAllUsers() {
        return null;
    }

    @Override
    public Role getUserRole(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Role role = optionalUser.orElseThrow().getRole();
        return role;
    }



    @PostConstruct
    public void setData(){
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setUserName("admin");
        user.setPassword("admin123"); //TODO encode
        user.setFirstName("Mohamed");
        user.setLastName("Adel");
        userRepository.save(user);
    }
}
