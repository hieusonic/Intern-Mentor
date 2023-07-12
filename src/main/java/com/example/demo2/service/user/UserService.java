package com.example.demo2.service.user;

import com.example.demo2.entity.user.User;


public interface UserService {
    boolean save(User user);
    User getOneByEmailAndPass(User user);

    User getOneByEmail(String email);

    User updateUser(User user);

}
