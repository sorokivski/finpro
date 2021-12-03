package com.project.nix.controller.bill;


import com.project.nix.model.dtos.BillManipulationDTO;
import com.project.nix.model.enums.BillManipulationTypeEnum;
import com.project.nix.service.bill.BillManipulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BillControllerImpl implements BillController {
    private final BillManipulationService billManipulationService;

    @Override
    public BillManipulationDTO addManipulation(BillManipulationDTO billManipulationDTO, Authentication authentication) {
        return billManipulationService.add(billManipulationDTO, authentication);
    }

    @Override
    public void removeManipulation(String manipulationUUID, Authentication authentication) {
        billManipulationService.remove(manipulationUUID, authentication);
    }

    @Override
    public List<BillManipulationDTO> getAllBillManipulation(Authentication authentication) {
        return billManipulationService.getAll(authentication);
    }

    @Override
    public List<BillManipulationDTO> getBillManipulationByType(String type, Authentication authentication) {
        return billManipulationService.getByType(authentication, BillManipulationTypeEnum.valueOf(type));
    }

}

