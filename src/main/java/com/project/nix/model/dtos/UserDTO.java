package com.project.nix.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO extends BaseEntityDTO {
    private String name;
    private String surname;
    private BigDecimal moneyAmount;
    private String email;
    private String password;
    private Set<AuthorityDTO> authorities;
    private List<BillManipulationDTO> billManipulations;
}

