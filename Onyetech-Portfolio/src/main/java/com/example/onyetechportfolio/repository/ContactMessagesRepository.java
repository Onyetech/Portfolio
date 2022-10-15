package com.example.onyetechportfolio.repository;

import com.example.onyetechportfolio.model.ContactMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessagesRepository extends JpaRepository<ContactMessages, Long> {
}
