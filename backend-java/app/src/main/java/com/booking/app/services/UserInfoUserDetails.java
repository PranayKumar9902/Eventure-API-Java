package com.booking.app.services;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.booking.app.entities.User;

public class UserInfoUserDetails implements UserDetails{

    private String email;
    private String password;

    List<GrantedAuthority> authorities;

    public UserInfoUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = List.of((GrantedAuthority) () -> user.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    
}
