package com.example.taskslist.repository;

import com.example.taskslist.model.Task;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TasksRepository extends CrudRepository<Task, Integer> {


    @Modifying
    @Query("INSERT INTO tasks VALUES (NULL, :task_name, :notes, :status)")
    void storeTask(String task_name, String notes, String status);

    @Query("SELECT * FROM tasks ORDER BY id ASC")
    List<Task> findAllTasks();

    @Query("SELECT * FROM tasks WHERE id = :id")
    Task findTaskById(int id);

    @Query("SELECT * FROM tasks ORDER BY status ASC")
    List<Task> orderTasksByStatus();

    @Query("SELECT * FROM tasks ORDER BY task_name ASC")
    List<Task> orderTasksByName();

    @Modifying
    @Query("UPDATE tasks SET status = :status WHERE id = :id")
    void updateTask(String status, int id);

    @Modifying
    @Query("DELETE FROM tasks WHERE id = :id")
    void deleteTask(int id);

}
