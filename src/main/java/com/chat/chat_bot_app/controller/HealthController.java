package com.chat.chat_bot_app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@CrossOrigin
@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public void healthCheck(){
        System.out.println("Health End point call : "+ Instant.now());
    }
    @GetMapping("/test")
    public String healthCheckTest(){
        System.out.println("Health End point test call : "+ Instant.now());
        return "Server is up. Response from server : "+Instant.now();
    }
}
