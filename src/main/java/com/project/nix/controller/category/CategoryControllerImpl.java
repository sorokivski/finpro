package com.project.nix.controller.category;

import com.project.nix.model.dtos.CategoryDTO;
import com.project.nix.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;


    @Override
    public void addCategory(CategoryDTO categoryDTO, Authentication authentication) {
        categoryService.add(categoryDTO, authentication);
    }

    @Override
    public void removeCategory(String categoryUUID, Authentication authentication) {
        categoryService.remove(categoryUUID);
    }

    @Override
    public List<CategoryDTO> getAllUserCategories(Authentication authentication) {
        return categoryService.getAllCategories(authentication);
    }

    @Override
    public List<CategoryDTO> getUserCategoryByType(String description, Authentication authentication) {
        return categoryService.getByDescription(authentication, description);
    }

}
