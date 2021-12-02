package com.project.nix.service.auth;

import com.project.nix.model.entities.Authority;
import com.project.nix.model.enums.AuthorityEnum;
import lombok.NonNull;

public interface AuthorityService
{
    Authority getAuthority(@NonNull AuthorityEnum authorityEnum);
}
