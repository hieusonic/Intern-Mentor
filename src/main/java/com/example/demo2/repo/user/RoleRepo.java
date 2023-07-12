package com.example.demo2.repo.user;

import com.example.demo2.entity.user.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepo extends MongoRepository<Role, String> {
    Role findByName(String name);
}
