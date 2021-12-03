package com.project.nix.repository;

import com.project.nix.model.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> getAuthorityByAuthority(String authority);

    Boolean existsAuthorityByAuthority(String authority);
}
