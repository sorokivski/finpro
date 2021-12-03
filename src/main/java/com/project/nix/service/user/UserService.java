package com.project.nix.service.user;

import com.project.nix.model.dtos.UserDTO;
import com.project.nix.model.entities.BillManipulation;
import com.project.nix.model.entities.User;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;

public interface UserService {
    void register(@NonNull UserDTO userDTO);

    String authentication(@NonNull UserDTO userDTO);

    User getUserByAuthentication(@NonNull Authentication authentication);

    User withdraw(@NonNull Authentication authentication, @NonNull BigDecimal amount);

    User addMoney(@NonNull Authentication authentication, @NonNull BigDecimal amount);

    User removeBillManipulation(@NonNull BillManipulation billManipulation);

    UserDTO getData(@NonNull Authentication authentication);
}
