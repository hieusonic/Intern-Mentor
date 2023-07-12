package com.example.demo2.service.user;

import com.example.demo2.entity.user.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    void addRoleToUser(String phoneNumber, String roleName);

    Role findByName(String name);

    List<Role> getAll();
}
