package com.project.nix.mapper;

import com.project.nix.model.dtos.BillManipulationDTO;
import com.project.nix.model.entities.BillManipulation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillManipulationMapper {
    BillManipulation billManipulationDTOtoBillManipulation(BillManipulationDTO billManipulationDTO);

    BillManipulationDTO billManipulationToBillManipulationDTO(BillManipulation billManipulation);
}
