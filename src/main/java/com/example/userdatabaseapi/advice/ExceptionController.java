package com.example.userdatabaseapi.advice;

import com.example.userdatabaseapi.exceptions.IncompleteDetailsException;
import com.example.userdatabaseapi.dto.RequestDetails;
import com.example.userdatabaseapi.exceptions.InvalidTaskException;
import com.example.userdatabaseapi.service.ResponseMessageService;
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
        return responseMessageService.createMessage("Task details incomplete. Please check your input.");
    }

    @ExceptionHandler(InvalidTaskException.class)
    public ResponseEntity<?> exceptionInvalidTaskHandler()
    {
        return responseMessageService.createMessage("Invalid input, task does not exist");
    }


}
