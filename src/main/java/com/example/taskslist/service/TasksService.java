package com.example.taskslist.service;

import com.example.taskslist.exceptions.IncompleteDetailsException;
import com.example.taskslist.exceptions.InvalidTaskException;
import com.example.taskslist.model.Task;
import com.example.taskslist.repository.TasksRepository;
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
        if (newTask.getTaskName().isEmpty() || newTask.getNotes().isEmpty())
        {
            throw new IncompleteDetailsException();
        }

        tasksRepository.storeTask(newTask.getTaskName(), newTask.getNotes(), "incomplete");
    }

    public List<Task> displayTasks()
    {
        return tasksRepository.findAllTasks();
    }

    public List<Task> orderTasksUsingStatus()
    {
        return tasksRepository.orderTasksByStatus();
    }
    public List<Task> orderTasksUsingName()
    {
        return tasksRepository.orderTasksByName();
    }

    public Task displayTaskById(int id)
    {
        checkTaskExistence(id);
        return tasksRepository.findTaskById(id);
    }

    public void updateTask(String status, int id)
    {
        checkTaskExistence(id);
        tasksRepository.updateTask(status, id);
    }

    public void deleteTask(int id)
    {
        checkTaskExistence(id);
        tasksRepository.deleteTask(id);

    }

    private void checkTaskExistence(int id)
    {
        if (tasksRepository.findTaskById(id) == null)
           {
               throw new InvalidTaskException();
           }
    }

}
