package com.example.onyetechportfolio.controller;

import com.example.onyetechportfolio.dto.ContactMessagesRequest;
import com.example.onyetechportfolio.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/message")
public class ContactMessagesController {
    @Autowired
    private ContactMessageService service;

    @PostMapping("/sendMessage")
    private String saveMessages(@RequestBody ContactMessagesRequest request){
        service.getMessages(request);
        return "Message has been added successfully! Thank you";
    }
}
