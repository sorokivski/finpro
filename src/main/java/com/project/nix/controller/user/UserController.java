package com.project.nix.controller.user;

import com.project.nix.model.dtos.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static com.project.nix.Routes.USERS;

@RequestMapping(USERS)
public interface UserController {
    @PostMapping("/registration")
    void registration(@RequestBody UserDTO userDTO);

    @PostMapping("/authentication")
    Map<String, String> authentication(@RequestBody UserDTO userDTO);

    @GetMapping("/me")
    UserDTO getData(Authentication authentication);
}