package com.myspringproject.my_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myspringproject.my_api.entities.UserEntity;
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
        if(service.cangetmail(name)){
            return service.getemail(name);
        }else {
            return "failed";
        }
    }
    
     @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = service.authenticate(username, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
    
    @GetMapping("/welcome")
    public String first_api(){
        return "Welcome to my first API project";
    }

    @GetMapping("/mydata")
    public List<UserEntity> getAllEntities() {
        return service.getAllData();
    }
}
