package com.example.onyetechportfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessagesRequest {
    private String name;
    private String email;
    private String message;
}
