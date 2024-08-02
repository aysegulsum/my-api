package com.myspringproject.my_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myspringproject.my_api.services.UserService;

@RestController
@RequestMapping("/first")
public class controllerFirst {
 
    private final UserService service;

    //@Autowired
    public controllerFirst(UserService service) {
        this.service = service;
    }

    @GetMapping("/getemail")
    public String getUserMail(@RequestParam String name) {
            return service.getemail(name);
      
    }
    
    @GetMapping("/welcome")
    public String first_api(){
        return "Welcome to my first API project";
    }

}
