package com.project.nix.service.bill;

import com.project.nix.exception.BillServiceException;
import com.project.nix.mapper.BillManipulationMapper;
import com.project.nix.model.dtos.BillManipulationDTO;
import com.project.nix.model.entities.BillManipulation;
import com.project.nix.model.entities.User;
import com.project.nix.model.enums.BillManipulationTypeEnum;
import com.project.nix.repository.BillManipulationRepository;
import com.project.nix.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillManipulationServiceImpl implements BillManipulationService {
    private final BillManipulationRepository billManipulationRepository;
    private final BillManipulationMapper billManipulationMapper;
    private final UserService userService;

    @Override
    public BillManipulationDTO add(BillManipulationDTO billManipulationDTO, Authentication authentication) {

        BillManipulation billManipulation = billManipulationMapper
                .billManipulationDTOtoBillManipulation(billManipulationDTO);

        User user;

        if (billManipulation.getType().equals(BillManipulationTypeEnum.INCOME))
            user = userService.addMoney(authentication, billManipulationDTO.getBillAmount());
        else if (billManipulation.getType().equals(BillManipulationTypeEnum.COSTS))
            user = userService.withdraw(authentication, billManipulationDTO.getBillAmount());
        else
            throw new BillServiceException(BillServiceException.WRONG_MANIPULATION_TYPE);


        billManipulation.setUser(user);

        return billManipulationMapper
                .billManipulationToBillManipulationDTO(billManipulationRepository.save(billManipulation));
    }

    @Override
    @Transactional
    public void remove(String billManipulationUUID, Authentication authentication) {
        billManipulationRepository.getBillManipulationByEntityUUIDAndUserEmail(billManipulationUUID, authentication.getName())
                .map(billManipulation -> {
                    userService.removeBillManipulation(billManipulation);
                    billManipulationRepository.delete(billManipulation);
                    return billManipulation;
                })
                .orElseThrow(() -> new BillServiceException(BillServiceException.REMOVE_FORBIDDEN));
    }

    @Override
    public List<BillManipulationDTO> getAll(Authentication authentication) {
        return billManipulationRepository.getAllByUserEmail(authentication.getName()).stream()
                .map(billManipulationMapper::billManipulationToBillManipulationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BillManipulationDTO> getByType(Authentication authentication, BillManipulationTypeEnum billManipulationType) {
        return billManipulationRepository.getAllByUserEmailAndType(authentication.getName(), billManipulationType).stream()
                .map(billManipulationMapper::billManipulationToBillManipulationDTO)
                .collect(Collectors.toList());
    }

}
