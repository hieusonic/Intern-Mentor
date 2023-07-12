package com.example.demo2.repo.mission;

import com.example.demo2.entity.mission.Task;
import com.example.demo2.entity.person.Intern;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends MongoRepository<Task, Integer> {
    List<Task> findAll();
    //Tìm kiếm các công việc theo trạng thái:
    List<Task> findByCompleted(boolean completed);
    //Xóa các công việc theo trạng thái:
    Optional<Task> findById(Integer id);

    // Tìm kiếm các công việc có tên chứa một từ khóa:
    List<Task> findByTaskNameContaining(String keyword);


}
