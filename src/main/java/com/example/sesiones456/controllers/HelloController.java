package com.example.sesiones456.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    public HelloController(){

    }

    @Value("${app.message}")
    private String message;

    @GetMapping("/holamundo")
    public String saludar(){
        return message;
    }

}
