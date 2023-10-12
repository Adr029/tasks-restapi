package com.example.userdatabaseapi.service;

import com.example.userdatabaseapi.dto.RequestDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseMessageService {

    public ResponseEntity<?> createMessage(String message)
    {
        RequestDetails requestDetails = new RequestDetails();
        requestDetails.setMessage(message);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(requestDetails);
    }
}
