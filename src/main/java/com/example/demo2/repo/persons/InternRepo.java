package com.example.demo2.repo.persons;

import com.example.demo2.entity.person.Intern;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface InternRepo extends MongoRepository<Intern,Integer> {
    Optional<Intern> findById(Integer id);

    List <Intern> findAll();

}
