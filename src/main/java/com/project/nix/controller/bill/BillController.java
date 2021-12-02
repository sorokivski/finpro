package com.project.nix.controller.bill;


import com.project.nix.model.dtos.BillManipulationDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.nix.Routes.BILLS;

@RequestMapping(BILLS)
public interface BillController
{
    @PostMapping("/add/manipulation")
    BillManipulationDTO addManipulation(@RequestBody BillManipulationDTO moneyManipulationDTO,
                                        Authentication authentication);

    @DeleteMapping("/remove/manipulation/{manipulation_uuid}")
    void removeManipulation(@PathVariable("manipulation_uuid") String manipulationUUID, Authentication authentication);

    @GetMapping("/get/all/manipulations")
    List<BillManipulationDTO> getAllBillManipulation(Authentication authentication);

    @GetMapping("/get/manipulations/by/type/{type}")
    List<BillManipulationDTO> getBillManipulationByType(@PathVariable("type") String type,
                                                          Authentication authentication);

}