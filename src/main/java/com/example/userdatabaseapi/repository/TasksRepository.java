package com.example.userdatabaseapi.repository;

import com.example.userdatabaseapi.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TasksRepository {
    private final JdbcTemplate jdbc;

    public TasksRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public void storeTask(Task task) {
        String sql = "INSERT INTO tasks VALUES (NULL, ?, ?, ?, ?)";
        jdbc.update(sql, task.getTaskName(), task.getDate(), task.getNotes(), "incomplete");
    }
    public List<Task> findAllTasks() {
        String sql = "SELECT * FROM tasks ORDER BY id ASC";
        return jdbc.query(sql, tasksRowMapper());
    }
    public Task findTaskbyId(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        return jdbc.queryForObject(sql,tasksRowMapper(),id);
    }
    public void updateTask(int id, String status){
        String sql = "UPDATE tasks SET status = ? WHERE id = ?";
        jdbc.update(sql, status, id);
    }
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbc.update(sql, id);
    }

    public  List<Task> orderTasksbyStatus()
    {
        String sql = "SELECT * FROM tasks ORDER BY status ASC";
        return jdbc.query(sql, tasksRowMapper());    }

    public List<Task> orderTasksbyName()
    {
        String sql = "SELECT * FROM tasks ORDER BY task_name ASC";
        return jdbc.query(sql, tasksRowMapper());
    }

    private RowMapper<Task> tasksRowMapper()
    {
        return (r, i) -> {
            Task rowObject = new Task();
            rowObject.setId(r.getInt("id"));
            rowObject.setTaskName(r.getString("task_name"));
            rowObject.setDate(r.getString("date_target"));
            rowObject.setNotes(r.getString("notes"));
            rowObject.setStatus(r.getString("status"));
            return rowObject;
        };
    }
}

