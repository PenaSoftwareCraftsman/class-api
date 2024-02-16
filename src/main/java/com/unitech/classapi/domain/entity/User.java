package com.unitech.classapi.domain.entity;

import com.unitech.classapi.domain.enums.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
public class User implements UserDetails{
    public UUID id;
    private String name;
    private String password;
    private String email;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.SECRETARY) return List.of(new SimpleGrantedAuthority("ROLE_SECRETARY"), new SimpleGrantedAuthority("ROLE_TEACHER"));

        return List.of(new SimpleGrantedAuthority("ROLE_TEACHER"));
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
