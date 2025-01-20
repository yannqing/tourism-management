package com.qcx.property.domain.model;

import com.qcx.property.domain.entity.Role;
import com.qcx.property.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private List<SimpleGrantedAuthority> simpleGrantedAuthorities;

    @Getter
    @Setter
    private List<Role> role;

    @Getter
    private final User user;

    public SecurityUser(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        String password = user.getPassword();
        user.setPassword(null);
        return password;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountNoExpired() == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountNoLocked() == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsNoExpired() == 1;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled() == 1;
    }
}
