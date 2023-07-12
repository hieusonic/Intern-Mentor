package com.example.demo2.service.mission;

import com.example.demo2.entity.mission.Task;
import com.example.demo2.entity.person.Intern;

import java.util.List;

public interface TaskService {
    Task save(Task task);
    List<Task> getAll();
    // service get theo thạng thái hoàn thành
    List<Task> getTaskComp(boolean completed);
    // delete theo thạng thái hoàn thành
    boolean deleteTask(Integer id);
    // get list theo từ khóa
    List<Task> getTaskKey(String keyword);

}
