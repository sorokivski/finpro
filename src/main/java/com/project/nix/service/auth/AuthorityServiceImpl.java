package com.project.nix.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.nix.exception.AuthorityServiceException;
import com.project.nix.model.entities.Authority;
import com.project.nix.model.enums.AuthorityEnum;
import com.project.nix.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;


    @PostConstruct
    private void doOnInit() throws JsonProcessingException {
        Stream.of(AuthorityEnum.values())
                .map(AuthorityEnum::name)
                .filter(authority -> !authorityRepository.existsAuthorityByAuthority(authority))
                .map(Authority::new)
                .forEach(authorityRepository::save);
    }

    @Override
    public Authority getAuthority(AuthorityEnum authorityEnum) {
        return authorityRepository.getAuthorityByAuthority(authorityEnum.name())
                .orElseThrow(() -> new AuthorityServiceException(AuthorityServiceException.AUTHORITY_NOT_FOUND));
    }
}
