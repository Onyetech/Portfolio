package com.example.onyetechportfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity(name = "contact_messages")
public class ContactMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messg_id", nullable = false)
    private Long id;

    private String name;
    private String email;
    private String message;

}
