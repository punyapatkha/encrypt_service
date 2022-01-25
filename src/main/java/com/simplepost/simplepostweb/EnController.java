package com.simplepost.simplepostweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class EnController {

    @GetMapping()
    public String getUsers(){
        return "HTTP GET request was sent";
    }

    @PostMapping()
    public String PostUsers(){
        return "HTTP POST request was sent";
    }

}

