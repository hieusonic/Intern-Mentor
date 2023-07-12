package com.example.demo2.service.mission;

import com.example.demo2.entity.mission.Task;
import com.example.demo2.repo.mission.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements  TaskService{
    @Autowired
    TaskRepo taskRepo;
    @Override
    public List<Task> getAll(){
        return taskRepo.findAll();
    }
    @Override
    public Task save(Task task){
        return taskRepo.save(task);
    }

    @Override
    public List<Task> getTaskComp(boolean completed){

        return taskRepo.findByCompleted(completed);
    }
    @Override
    public boolean deleteTask(Integer id){
        taskRepo.deleteById(id);
        return true;
    }
    @Override
    public List<Task> getTaskKey(String keyword){
        return taskRepo.findByTaskNameContaining(keyword);
    }

}
