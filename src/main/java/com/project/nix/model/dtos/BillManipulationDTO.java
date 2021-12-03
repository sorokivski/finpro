package com.project.nix.model.dtos;


import com.project.nix.model.enums.BillManipulationTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BillManipulationDTO extends BaseEntityDTO {
    private BigDecimal billAmount;
    private BillManipulationTypeEnum type;
}
