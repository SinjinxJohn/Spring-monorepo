package com.example.todo.Controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class healthCheck {


    @Value("${server.port}")
    private String port;
    @GetMapping("/health-check")
    public String healthCheck(){
        return "Hello From instance running on port " + port + " at " + System.currentTimeMillis();
    }
}
