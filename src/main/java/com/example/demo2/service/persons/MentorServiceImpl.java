package com.example.demo2.service.persons;

import com.example.demo2.entity.person.Intern;
import com.example.demo2.entity.person.Mentor;
import com.example.demo2.repo.persons.MentorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImpl implements MentorService{
    @Autowired
    MentorRepo mentorRepo;
    @Override
    public List<Mentor> getAll(){

        return mentorRepo.findAll();
    }
    @Override
    public Mentor getOneById(Integer id){
        return mentorRepo.findById(id).get();
    }
    @Override
    public Mentor save(Mentor mentor){
        return mentorRepo.save(mentor);
    }
    @Override
    public Mentor update(Mentor mentor){
        return mentorRepo.save(mentor);
    }
    @Override
    public boolean deleteOneById(Integer id) {
        mentorRepo.deleteById(id);
        return true;
    }
}
