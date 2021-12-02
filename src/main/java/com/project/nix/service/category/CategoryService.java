package com.project.nix.service.category;

import com.project.nix.model.dtos.CategoryDTO;
import com.project.nix.model.entities.Category;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CategoryService {
    Category add(@NonNull CategoryDTO categoryDTO, @NonNull Authentication authentication);

    void remove(@NonNull String categoryUUID);

    List<CategoryDTO> getAllCategories(@NonNull Authentication authentication);

    List<CategoryDTO> getByDescription(@NonNull Authentication authentication, @NonNull String description);
}
