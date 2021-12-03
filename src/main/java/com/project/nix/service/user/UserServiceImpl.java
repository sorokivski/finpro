package com.project.nix.service.user;


import com.project.nix.exception.BillServiceException;
import com.project.nix.exception.UserServiceException;
import com.project.nix.mapper.UserMapper;
import com.project.nix.model.dtos.UserDTO;
import com.project.nix.model.entities.BillManipulation;
import com.project.nix.model.entities.User;
import com.project.nix.model.enums.AuthorityEnum;
import com.project.nix.model.enums.BillManipulationTypeEnum;
import com.project.nix.repository.UserRepository;
import com.project.nix.service.JWTService;
import com.project.nix.service.auth.AuthorityService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityService authorityService;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDTO userDTO) {
        Optional.ofNullable(userRepository.existsUserByEmail(userDTO.getEmail()))
                .filter(isExists -> !isExists)
                .map(ignore -> userMapper.userDTOtoUser(userDTO))
                .map(user -> {
                    user.setAuthorities(Set.of(authorityService.getAuthority(AuthorityEnum.USER)));
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return user;
                })
                .map(userRepository::save)
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new UserServiceException(UserServiceException.EXISTS_USER_BY_EMAIL));
    }

    @Override
    public String authentication(UserDTO userDTO) {
        return userRepository.getUserByEmail(userDTO.getEmail())
                .filter(user -> passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
                .map(jwtService::generate)
                .orElseThrow(() -> new UserServiceException(UserServiceException.WRONG_EMAIL_OR_PASSWORD));
    }

    @Override
    public User getUserByAuthentication(Authentication authentication) {
        return userRepository.getUserByEmail(authentication.getName())
                .orElseThrow(() -> new UserServiceException(UserServiceException.WRONG_AUTHENTICATION_DATA));
    }

    @Override
    public User addMoney(Authentication authentication, BigDecimal amount) {
        return manipulateWithMoney(authentication, amount, BillManipulationTypeEnum.INCOME);
    }

    @Override
    public User withdraw(Authentication authentication, BigDecimal amount) {
        return manipulateWithMoney(authentication, amount, BillManipulationTypeEnum.COSTS);
    }

    @Override
    public User removeBillManipulation(@NonNull BillManipulation billManipulation) {
        User user = switch (billManipulation.getType()) {
            case INCOME -> manipulateWithMoney(billManipulation.getUser(), billManipulation.getMoneyAmount(), BillManipulationTypeEnum.COSTS);
            case COSTS -> manipulateWithMoney(billManipulation.getUser(), billManipulation.getMoneyAmount(), BillManipulationTypeEnum.INCOME);
            default -> throw new UserServiceException(BillServiceException.WRONG_MANIPULATION_TYPE);
        };

        return userRepository.save(user);
    }

    @Override
    public UserDTO getData(Authentication authentication) {
        return userRepository.getUserByEmail(authentication.getName())
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new UserServiceException(UserServiceException.WRONG_AUTHENTICATION_DATA));
    }

    private User manipulateWithMoney(Authentication authentication, BigDecimal amount, BillManipulationTypeEnum moneyManipulationType) {
        return manipulateWithMoney(getUserByAuthentication(authentication), amount, moneyManipulationType);
    }

    private User manipulateWithMoney(User user, BigDecimal amount, BillManipulationTypeEnum moneyManipulationType) {
        amount = amount.abs();

        BigDecimal userAmount = user.getMoneyAmount();

        user.setMoneyAmount(moneyManipulationType == BillManipulationTypeEnum.INCOME ? userAmount.add(amount) : userAmount.subtract(amount));

        return userRepository.save(user);
    }
}
