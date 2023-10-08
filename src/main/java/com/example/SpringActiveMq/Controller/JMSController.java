package com.example.SpringActiveMq.Controller;

import com.example.SpringActiveMq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JMSController {
    @Autowired
    MessageSender messageSender;
    @GetMapping("/{message}")
    public void sendMessage(@PathVariable String message){
        messageSender.sendMessage("MESSAGE_QUEUE",message);
    }
}
