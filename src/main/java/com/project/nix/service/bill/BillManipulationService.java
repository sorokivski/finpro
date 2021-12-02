package com.project.nix.service.bill;

import com.project.nix.model.dtos.BillManipulationDTO;
import com.project.nix.model.enums.BillManipulationTypeEnum;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BillManipulationService
{
    BillManipulationDTO add(@NonNull BillManipulationDTO billManipulationDTO, @NonNull Authentication authentication);

    void remove(@NonNull String moneyManipulation, @NonNull Authentication authentication);

    List<BillManipulationDTO> getAll(@NonNull Authentication authentication);

    List<BillManipulationDTO> getByType(@NonNull Authentication authentication,
                                        @NonNull BillManipulationTypeEnum billManipulationType);

}
