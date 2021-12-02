package com.project.nix.model.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "authorities")
@Entity
@ToString(of = {"authority"})
@NoArgsConstructor
@EqualsAndHashCode(of = {"authority"})
@AllArgsConstructor
public class Authority implements GrantedAuthority
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "authority")
    private String authority;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "authority_user",
            joinColumns = @JoinColumn(name = "authority_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    protected Set<User> users;

    public Authority(String authority)
    {
        this.authority = authority;
    }
}
