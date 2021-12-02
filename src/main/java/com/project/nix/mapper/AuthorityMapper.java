package com.project.nix.mapper;

import com.project.nix.model.entities.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthorityMapper
{
    @Mappings({
            @Mapping(target = "authority", source = "authority.authority")
    })
    Authority authorityToAuthorityDTO(Authority authority);
}
