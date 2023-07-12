package com.example.demo2.service.user;

import com.example.demo2.entity.user.Role;
import com.example.demo2.entity.user.User;
import com.example.demo2.repo.user.RoleRepo;
import com.example.demo2.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public Role saveRole(Role role) {
        roleRepo.save(role);
        return role;
    }

    @Override
    public void addRoleToUser(String phoneNumber, String roleName) {
        User user = userRepo.findByEmail(phoneNumber);
        Role role = roleRepo.findByName(roleName);
        // tạo môt list Kiểu Role và truyền vào getRoles trong user là một mảng những quyền của user
        // (List<Role>) ep kieu vì ban đầu dữ liệu dạng mảng  chyển thành List
        List<Role> roles = (List<Role>) user.getRoles();
        roles.add(role); // tim role trong co so du lieu roi thêm nó vào List roles
        user.setRoles(roles);
        userRepo.save(user);
    }

    @Override
    public Role findByName(String name) {

        return roleRepo.findByName(name);
    }

    @Override
    public List<Role> getAll() {

        return roleRepo.findAll();
    }
}
