package com.example.demo2.controller.persons;

import com.example.demo2.entity.ResponseDTO;
import com.example.demo2.entity.person.Intern;
import com.example.demo2.entity.person.Mentor;
import com.example.demo2.service.persons.InternService;
import com.example.demo2.service.persons.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Mentor")
public class MentorController {

        @Autowired
        MentorService mentorService;
        // phương thức get-all
        @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ResponseDTO<List<Mentor>>> getAllIntern(){
            List<Mentor> mentorsAll = mentorService.getAll();
            return ResponseEntity.ok(new ResponseDTO<List<Mentor>>(mentorsAll, "200", "Success"));
        }
        // phương thức get theo id
        @GetMapping(value = "/getId/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ResponseDTO<Mentor>> getMentor(@PathVariable("id") Integer id){
            Mentor mentor =mentorService.getOneById(id);
            if (mentor == null) {
                return ResponseEntity.ok(new ResponseDTO("không tìm thấy ", "404", "Failed"));
            }
            return ResponseEntity.ok(new ResponseDTO<Mentor>(mentor, "200", "Success"));
        }
        @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ResponseDTO<?>> saveMentor(@RequestBody Mentor mentor){
            Mentor mentor1 = mentorService.save(mentor);
            return ResponseEntity.ok(new ResponseDTO<Mentor>(mentor, "200", "Success"));
        }
        @PutMapping(value = "/update/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ResponseDTO<?>> updateMentor(@RequestBody Mentor mentor){
            Mentor mentor2 = mentorService.update(mentor);
            return ResponseEntity.ok(new ResponseDTO<Mentor>(mentor, "200", "Success"));
        }
        @DeleteMapping(value ="/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public  ResponseEntity<ResponseDTO<Mentor>> deleteID (@PathVariable Integer id){
            boolean rs = mentorService.deleteOneById(id);
            if (!rs) {
                return ResponseEntity.ok(new ResponseDTO("Xoá sách không thành công", "404", "Failed"));
            }
            return ResponseEntity.ok(new ResponseDTO("Xoá sách thành công", "200", "Success"));

        }
    }



