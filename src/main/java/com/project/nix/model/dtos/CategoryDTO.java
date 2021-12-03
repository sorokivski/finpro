package com.project.nix.model.dtos;


import com.project.nix.model.entities.BillManipulation;
import com.project.nix.model.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
public class CategoryDTO extends BaseEntityDTO {
    private String description;
    private Set<BillManipulation> manipulations;
    private User user;
}
