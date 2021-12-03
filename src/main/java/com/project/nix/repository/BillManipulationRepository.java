package com.project.nix.repository;

import com.project.nix.model.entities.BillManipulation;
import com.project.nix.model.enums.BillManipulationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillManipulationRepository extends JpaRepository<BillManipulation, Long> {
    Optional<BillManipulation> getBillManipulationByEntityUUID(String entityUUID);

    Optional<BillManipulation> getBillManipulationByEntityUUIDAndUserEmail(String entityUUID, String email);

    List<BillManipulation> getAllByUserEmail(String email);

    List<BillManipulation> getAllByUserEmailAndType(String email, BillManipulationTypeEnum type);

}
