package com.hoaxify.hoaxifybackend.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity(name = "users")
public class User implements UserDetails {
    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "{hoaxify.constraint.username.notnull.message}")
    @Size(min = 4, max = 140, message = "Length of username must be between {min} and {max}")
    @UniqueUsername
    private String username;

    @NotNull(message = "{hoaxify.constraint.displayName.notnull.message}")
    @Size(min = 4, max = 140, message = "Length of display name must be between {min} and {max}")
    private String displayName;

    @NotNull(message = "{hoaxify.constraint.password.notnull.message}")
    @Size(min = 8, max = 140, message = "Length of password name must be between {min} and {max}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{hoaxify.constraint.password.pattern.message}")
    private String password;

    private String image;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
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
