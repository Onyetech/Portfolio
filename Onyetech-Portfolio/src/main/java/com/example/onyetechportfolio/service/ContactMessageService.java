package com.example.onyetechportfolio.service;

import com.example.onyetechportfolio.dto.ContactMessagesRequest;
import com.example.onyetechportfolio.model.ContactMessages;


public interface ContactMessageService {
    public ContactMessages saveMessage(ContactMessages message);

    public String getMessages(ContactMessagesRequest request);
}
