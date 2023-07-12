package com.example.demo2.entity.user;

import com.example.demo2.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    private String id;
    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private Collection<Role> roles = new ArrayList<>(); // danh sach cac quyen ma user co



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // tao ra mot mang moi chua cac doi tuong SimpleGrantedAuthority
        Collection<SimpleGrantedAuthority> role = new ArrayList<>();
        // tu danh sach các quyen ma user co (roles) qua vong lap for va các hàm biến đổi để biến đổi các quyền thành đối tượng
        roles.stream().forEach(role1 -> role.add(new SimpleGrantedAuthority(role1.getName())));
        return role;
    }

    public User(String email, String name, String address, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
