package com.example.demo2.service.persons;

import com.example.demo2.entity.person.Intern;
import com.example.demo2.entity.person.Mentor;

import java.util.List;

public interface MentorService {
    // lấy tất cả các mentor
    List<Mentor> getAll();
    // lấy mentor theo id ;
    Mentor getOneById(Integer id);
    // thêm mentor
    Mentor save(Mentor mentor);
    // sửa, update
    Mentor update(Mentor mentor);
    // xóa
    boolean deleteOneById(Integer id);
}
