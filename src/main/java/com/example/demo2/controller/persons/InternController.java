package com.example.demo2.controller.persons;

import com.example.demo2.entity.ResponseDTO;
import com.example.demo2.entity.person.Intern;
import com.example.demo2.service.persons.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intern")
public class InternController {
    @Autowired
    InternService internService;
    // phương thức get-all
    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<Intern>>> getAllIntern(){
        List<Intern> internsAll = internService.getAll();
        return ResponseEntity.ok(new ResponseDTO<List<Intern>>(internsAll, "200", "Success"));
    }
    // phương thức get theo id
    @GetMapping(value = "/getId/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Intern>> getIntern(@PathVariable ("id") Integer id){
        Intern intern =internService.getOneById(id);
        if (intern == null) {
            return ResponseEntity.ok(new ResponseDTO("không tìm thấy ", "404", "Failed"));
        }

        return ResponseEntity.ok(new ResponseDTO<Intern>(intern, "200", "Success"));
    }
    @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<?>> saveIntern(@RequestBody Intern intern){
        Intern intern1 = internService.save(intern);
        return ResponseEntity.ok(new ResponseDTO<Intern>(intern, "200", "Success"));
    }
    @PutMapping(value = "/update/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<?>> updateIntern(@RequestBody Intern intern){
        Intern intern2 = internService.update(intern);
        return ResponseEntity.ok(new ResponseDTO<Intern>(intern, "200", "Success"));
    }
    @DeleteMapping(value ="/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<ResponseDTO<Intern>> deleteID (@PathVariable Integer id){
       boolean rs = internService.deleteOneById(id);
        if (!rs) {
            return ResponseEntity.ok(new ResponseDTO("Xoá không thành công", "404", "Failed"));
        }
        return ResponseEntity.ok(new ResponseDTO("Xoá thành công", "200", "Success"));

    }
}

