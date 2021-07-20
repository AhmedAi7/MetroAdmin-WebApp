package com.javacode.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javacode.Model.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private static String ROLE_PREFIX = "ROLE_";

    private Integer user_id;
    private String user_name;
    private String email;
    @JsonIgnore
    private String password;
    private String role;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Integer user_id, String user_name, String email, String password, String role,
                           Collection<? extends GrantedAuthority> authorities) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    public static CustomUserDetails build(Admin admin) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + "admin"));

        return new CustomUserDetails(
                admin.getId(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getPassword(),
                "admin",
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + getRole()));
        return list;
    }

    public int getId() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole()
    {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return user_name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CustomUserDetails user = (CustomUserDetails) o;
        return Objects.equals(user_id, user.user_id);
    }
}
