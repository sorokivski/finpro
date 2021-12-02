package com.project.nix.model.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Table(name = "users")
@Entity
@ToString(of = {"name", "surname", "email"})
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "money_amount", nullable = false)
    private BigDecimal moneyAmount = new BigDecimal(0);

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BillManipulation> billManipulations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categories;

    @Override
    protected void doOnCreate() {
        super.doOnCreate();
        moneyAmount = new BigDecimal(0);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
