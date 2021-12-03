package com.project.nix.service.category;

import com.project.nix.exception.CategoryServiceException;
import com.project.nix.mapper.CategoryMapper;
import com.project.nix.model.dtos.CategoryDTO;
import com.project.nix.model.entities.Category;
import com.project.nix.model.entities.User;
import com.project.nix.repository.CategoryRepository;
import com.project.nix.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserService userService;

    @Override
    public Category add(@NonNull CategoryDTO categoryDTO, @NonNull Authentication authentication) {
        Category category = categoryMapper
                .categoryDTOtoCategory(categoryDTO);
        User user = userService.getUserByAuthentication(authentication);

        if (!categoryRepository.existsCategoryByDescriptionAndUserEmail(category.getDescription(), user.getEmail()))
            return categoryRepository.save(category);
        else throw
                new CategoryServiceException(CategoryServiceException.DUPLICATE_CATEGORY);

    }

    @Override
    public void remove(@NonNull String categoryUUID) {
        categoryRepository.delete(categoryRepository.getCategoryById(Long.parseLong(categoryUUID))
                .orElseThrow(() -> new CategoryServiceException(CategoryServiceException.WRONG_CATEGORY_TYPE)));
    }

    @Override
    public List<CategoryDTO> getAllCategories(@NonNull Authentication authentication) {
        return categoryRepository.getAllByUserEmail(authentication.getName()).stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getByDescription(@NonNull Authentication authentication, @NonNull String description) {
        return categoryRepository.getAllByUserEmailAndDescription(authentication.getName(), description).stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }
}
