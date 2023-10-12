package com.example.taskslist.controller;

import com.example.taskslist.model.Task;
import com.example.taskslist.service.ResponseMessageService;
import com.example.taskslist.service.TasksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TasksService tasksService;
    private final ResponseMessageService responseMessageService;

    public TaskController(TasksService tasksService, ResponseMessageService responseMessageService) {
        this.tasksService = tasksService;
        this.responseMessageService = responseMessageService;
    }
    //display all tasks

    @GetMapping("/tasks")
    public List<Task> getTasks()
    {
       return tasksService.displayTasks();
    }

    // id starts from 1

    //display tasks by specific id

    @GetMapping("/tasks/{id}")
    public Task specifiedTask(@PathVariable int id)
    {
      return tasksService.displayTaskById(id);
    }

    //create new task
   @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody Task newTask)
    {
       tasksService.addTask(newTask);
       return responseMessageService.createMessage("Task Added Successfully", HttpStatus.ACCEPTED);
    }

    //update task status if it exists

    @PatchMapping("/tasks/{id}/update")
    public ResponseEntity<?> updateTaskStatus (
            @PathVariable int id,
            @RequestParam String status
    )
    {
       tasksService.updateTask(status, id);
       return responseMessageService.createMessage("Task Updated Successfully", HttpStatus.ACCEPTED);
    }

    //delete task if it exists

   @DeleteMapping("/tasks/{id}/delete")
    public ResponseEntity<?> deleteTask(
           @PathVariable int id
    )
    {
       tasksService.deleteTask(id);
       return responseMessageService.createMessage("Task Deleted Successfully", HttpStatus.ACCEPTED);
    }

    //display data in order

    @GetMapping("/tasks/orderby/status")
    public List<Task> orderTasksByStatus()
    {
        return tasksService.orderTasksUsingStatus();
    }
    @GetMapping("/tasks/orderby/name")
    public List<Task> orderTasksByName()
    {
        return tasksService.orderTasksUsingName();
    }

}
