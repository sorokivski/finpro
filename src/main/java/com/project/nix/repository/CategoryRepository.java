package com.project.nix.repository;


import com.project.nix.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> getCategoryById(Long id);

    List<Category> getAllByUserEmail(String email);

    List<Category> getAllByUserEmailAndDescription(String email, String description);

    Boolean existsCategoryByDescriptionAndUserEmail(String description, String userEmail);
}
