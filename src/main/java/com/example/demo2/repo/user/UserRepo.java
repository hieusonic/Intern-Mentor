package com.example.demo2.repo.user;

import com.example.demo2.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
        User findByEmailAndPassword(String email, String pass);

        User findByEmail(String email);
}
