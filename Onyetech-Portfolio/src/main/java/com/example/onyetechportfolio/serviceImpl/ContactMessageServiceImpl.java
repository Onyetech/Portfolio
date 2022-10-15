package com.example.onyetechportfolio.serviceImpl;

import com.example.onyetechportfolio.dto.ContactMessagesRequest;
import com.example.onyetechportfolio.model.ContactMessages;
import com.example.onyetechportfolio.repository.ContactMessagesRepository;
import com.example.onyetechportfolio.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageServiceImpl implements ContactMessageService {

    @Autowired
    private ContactMessagesRepository messagesRepository;

    @Override
    public ContactMessages saveMessage(ContactMessages message) {
        return messagesRepository.save(message);
    }

    @Override
    public String getMessages(ContactMessagesRequest request) {
        ContactMessages messages1 = new ContactMessages();

        messages1.setMessage(request.getMessage());
        messages1.setName(request.getName());
        messages1.setEmail(request.getEmail());

        saveMessage(messages1);

        return "Message is saved. Thank you.";
    }
}
