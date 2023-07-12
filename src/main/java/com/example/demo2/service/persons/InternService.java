package com.example.demo2.service.persons;

import com.example.demo2.entity.person.Intern;

import java.util.List;

public interface InternService {


    List<Intern> getAll();
    Intern getOneById(Integer id);
    Intern save(Intern intern);
    Intern update(Intern intern);
    boolean deleteOneById(Integer id);

}

