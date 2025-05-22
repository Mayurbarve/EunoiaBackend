package com.springjwt.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

@RestController

public class HomeController {

    @RequestMapping("/welcome")
    public String welcome() {
        String text = "Welcome to Eunoia!<br><br>";
        text += "Eunoia is a mental health application that provides a platform for users to track their mental health and well-being. " +
                "It offers various features such as mood tracking, journaling, and access to mental health resources.";

        return text;
    }
}
