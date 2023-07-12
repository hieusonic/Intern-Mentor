package com.example.demo2.controller.mission;

import com.example.demo2.entity.ResponseDTO;
import com.example.demo2.entity.mission.Task;
import com.example.demo2.entity.person.Mentor;
import com.example.demo2.service.mission.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    // get toàn bộ các Task
    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<Task>>> getAllTask() {
        List<Task> list1 = taskService.getAll();
        return ResponseEntity.ok(new ResponseDTO<List<Task>>(list1, "200", "Success"));
    }
    // get List các Task có trạng thái là đã hoàn thành (completed = true)
    @GetMapping(value = "/get-comp/{completed}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<Task>>> getTaskComp(@PathVariable("completed") boolean completed) {
        if (completed) {
            List<Task> completedTasks = taskService.getTaskComp(completed);
            return ResponseEntity.ok(new ResponseDTO<>(completedTasks, "200", "Success"));
        }
        return ResponseEntity.ok(new ResponseDTO("Không tìm thấy", "404", "Failed"));

    }

        // thêm mới một task
    @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<?>> saveTask(@RequestBody Task task){
        Task task1 = taskService.save(task);
        return ResponseEntity.ok(new ResponseDTO<Task>(task, "200", "Success"));
    }
    // chỉnh sửa một task theo id
    @PutMapping(value = "/update/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<?>> updateTask(@PathVariable("id") Integer id, @RequestBody Task task){
        Task task2 = taskService.save(task);
        return ResponseEntity.ok(new ResponseDTO<Task>(task, "200", "Success"));
    }
    // xóa task theo id .
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Task>> deleteTask(@PathVariable("id") Integer id){
        boolean rs =  taskService.deleteTask(id);
        if(!rs){
            return ResponseEntity.ok(new ResponseDTO("Xoá không thành công", "404", "Failed"));

        }
        return ResponseEntity.ok(new ResponseDTO("Xoá thành công", "200", "Success"));

    }
}
