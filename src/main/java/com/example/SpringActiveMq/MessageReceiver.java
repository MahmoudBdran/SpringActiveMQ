package com.example.SpringActiveMq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
    @JmsListener(destination = "MESSAGE_QUEUE")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
