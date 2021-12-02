package com.project.nix.mapper;

import com.project.nix.model.dtos.CategoryDTO;
import com.project.nix.model.entities.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "description", source = "categoryDTO.description")
    })
    Category categoryDTOtoCategory(CategoryDTO categoryDTO);
    @Mappings({
            @Mapping(target = "description", ignore = true)
    })
    CategoryDTO categoryToCategoryDTO(Category category);

    String getDescription(Category c);
}
