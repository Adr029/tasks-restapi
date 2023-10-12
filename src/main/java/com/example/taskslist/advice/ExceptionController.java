package com.example.taskslist.advice;

import com.example.taskslist.exceptions.IncompleteDetailsException;
import com.example.taskslist.exceptions.InvalidTaskException;
import com.example.taskslist.service.ResponseMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    private final ResponseMessageService responseMessageService;

    public ExceptionController(ResponseMessageService responseMessageService) {
        this.responseMessageService = responseMessageService;
    }

    @ExceptionHandler(IncompleteDetailsException.class)
    public ResponseEntity<?> exceptionIncompleteDetailsHandler()
    {
        return responseMessageService.createMessage("Task details incomplete. Please check your input.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTaskException.class)
    public ResponseEntity<?> exceptionInvalidTaskHandler()
    {
        return responseMessageService.createMessage("Invalid input, task does not exist", HttpStatus.BAD_REQUEST);
    }


}
