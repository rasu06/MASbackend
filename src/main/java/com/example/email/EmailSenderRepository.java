package com.example.email;

import org.springframework.stereotype.Repository;

@Repository
public interface EmailSenderRepository {
    public void send(String to,String email);
}
