package com.project.nix.model.entities;

import com.project.nix.model.enums.BillManipulationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Table(name = "bill_manipulations")
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class BillManipulation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private BigDecimal moneyAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BillManipulationTypeEnum type;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

//    @ManyToMany(mappedBy = "manipulations", fetch = FetchType.LAZY)
//    private Set<Category> categories;
}
