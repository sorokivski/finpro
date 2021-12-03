package com.project.nix.mapper;

import com.project.nix.model.dtos.UserDTO;
import com.project.nix.model.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "email", source = "userDTO.email"),
            @Mapping(target = "name", source = "userDTO.name"),
            @Mapping(target = "surname", source = "userDTO.surname"),
            @Mapping(target = "password", source = "userDTO.password"),
    })
    User userDTOtoUser(UserDTO userDTO);

    @Mappings({
            @Mapping(target = "password", ignore = true)
    })
    UserDTO userToUserDTO(User user);
}
