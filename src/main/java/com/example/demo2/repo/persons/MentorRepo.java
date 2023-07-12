package com.example.demo2.repo.persons;

import com.example.demo2.entity.person.Intern;
import com.example.demo2.entity.person.Mentor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MentorRepo extends MongoRepository<Mentor,Integer> {

    Optional<Mentor> findById(Integer id);
    List <Mentor> findAll();

}
