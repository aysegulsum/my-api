package com.myspringproject.my_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
            myService.getAllData();  // Servis üzerinden veri çekme
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
        // UserEntity sınıfının bir nesnesini oluşturuyoruz
        UserEntity person = new UserEntity(user_id, username, email, password);

        // Servis sınıfını kullanarak kişiyi ekliyoruz
        myService.addPerson(person);

        // Başarılı bir yanıt döndürüyoruz
        return ResponseEntity.ok("Person added successfully.");
    }
}
