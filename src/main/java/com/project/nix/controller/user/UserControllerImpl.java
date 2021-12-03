package com.project.nix.controller.user;

import com.project.nix.model.dtos.UserDTO;
import com.project.nix.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    @Value("${spring.security.jwt.name}")
    private String JWTName;

    @Override
    public void registration(UserDTO userDTO) {
        userService.register(userDTO);
    }

    @Override
    public Map<String, String> authentication(UserDTO userDTO) {
        return Map.of(JWTName, userService.authentication(userDTO));
    }

    @Override
    public UserDTO getData(Authentication authentication) {
        return userService.getData(authentication);
    }


}