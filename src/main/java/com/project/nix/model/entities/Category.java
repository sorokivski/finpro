package com.project.nix.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@Table(name = "categories")
@Entity
@ToString(of = {"user_id", "description", "manipulation_id"})
@NoArgsConstructor
@EqualsAndHashCode()
@AllArgsConstructor
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "categories",
//            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "manipulation_id", referencedColumnName = "id"))
//    private Set<BillManipulation> manipulations;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<BillManipulation> manipulationList;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
