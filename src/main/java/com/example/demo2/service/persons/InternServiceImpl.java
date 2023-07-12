package com.example.demo2.service.persons;

import com.example.demo2.entity.person.Intern;
import com.example.demo2.repo.persons.InternRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InternServiceImpl implements InternService{
    @Autowired
    InternRepo internRepo;
    @Override
    public List<Intern> getAll() {

        return internRepo.findAll();
    }
    @Override
    public Intern getOneById(Integer id){
        return internRepo.findById(id).get();
    }
    @Override
    public Intern save(Intern intern){
        return internRepo.save(intern);
    }
    @Override
    public Intern update(Intern intern){
        return internRepo.save(intern);
    }
    @Override
    public boolean deleteOneById(Integer id) {
        internRepo.deleteById(id);
        return true;
    }
}
