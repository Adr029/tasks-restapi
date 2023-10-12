package com.example.userdatabaseapi.service;

import com.example.userdatabaseapi.exceptions.IncompleteDetailsException;
import com.example.userdatabaseapi.exceptions.InvalidTaskException;
import com.example.userdatabaseapi.model.Task;
import com.example.userdatabaseapi.repository.TasksRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TasksService {
    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void addTask(Task newTask)
    {
        if (newTask.getTaskName().isEmpty() || newTask.getDate().isEmpty() || newTask.getNotes().isEmpty())
        {
            throw new IncompleteDetailsException();
        }
        tasksRepository.storeTask(newTask);
    }

    public List<Task> displayTasks()
    {
        return tasksRepository.findAllTasks();
    }

    public List<Task> orderTasksUsingStatus()
    {
        return tasksRepository.orderTasksbyStatus();
    }
    public List<Task> orderTasksUsingName()
    {
        return tasksRepository.orderTasksbyName();
    }

    public Task displayTaskbyId(int id)
    {
        checkTaskExistence(id);
        return tasksRepository.findTaskbyId(id);
    }

    public void updateTask(int id, String status)
    {
        tasksRepository.updateTask(id, status);

    }

    public void deleteTask(int id)
    {
        checkTaskExistence(id);
        tasksRepository.deleteTask(id);

    }

    private void checkTaskExistence(int id)
    {
        try
        {
            tasksRepository.findTaskbyId(id);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new InvalidTaskException();
        }
    }

}
