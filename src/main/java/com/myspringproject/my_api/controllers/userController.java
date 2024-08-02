package com.myspringproject.my_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/test")
public class userController {
    
   
    @Autowired
    private UserService myService;
    
    @GetMapping
    public String testDatabaseConnection() {
        try {
            myService.getAllData();
            return "Database connection is successful!";
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }

      @PostMapping("/add")
    public ResponseEntity<String> addPerson(@RequestParam long user_id, 
                                            @RequestParam String username, 
                                            @RequestParam String email, 
                                            @RequestParam String password) {
        
        UserEntity person = new UserEntity(user_id, username, email, password);

        if(myService.userExist(username, password).isSuccess()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
        else{
        if(myService.addPerson(person).isSuccess())
            return ResponseEntity.ok("Person added successfully.");
        
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myService.addPerson(person).getMessage());
        }
    }

      @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
     if(myService.userExist(username, password).isSuccess()){
        if (myService.authenticate(username, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
     }else
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user exists");
    }
    @GetMapping("/mydata")
    public List<UserEntity> getAllEntities() {
        return myService.getAllData();
    } 
}
