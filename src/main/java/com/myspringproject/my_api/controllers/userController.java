package com.myspringproject.my_api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myspringproject.my_api.entities.UserEntity;
import com.myspringproject.my_api.services.UserService;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserService myService;

    @GetMapping("/test")
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

        if (myService.userExist(username).isSuccess()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        } else {
            if (myService.addPerson(person).isSuccess()) {
                return ResponseEntity.ok("Person added successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myService.addPerson(person).getMessage());
            }
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (myService.userExist(username).isSuccess()) {
            if (myService.authenticate(username, password)) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password is not correct");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user exists");
        }
    }

    @GetMapping("/mydata")
    public List<UserEntity> getAllEntities() {
        return myService.getAllData();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String username, @RequestParam String password) {
        if (myService.userExist(username).isSuccess()) {
            if (myService.authenticate(username, password)) {
                if (myService.deleteUser(username, password).isSuccess()) {
                    return ResponseEntity.ok("Delete successful");
                } else {

                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(myService.deleteUser(username, password).getMessage());
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password is not correct!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user exists");
        }

    }

    @PutMapping("/updatename/{userName}")
public ResponseEntity<String> updateUserName(
        @PathVariable String userName,
        @RequestBody Map<String, String> request) {
    
    String password = request.get("password");
    String newName = request.get("newName");

    if (myService.userExist(userName).isSuccess()) {
        if (myService.authenticate(userName, password)) {
            if (myService.updateUserName(userName, newName)) {
                return ResponseEntity.ok("Username updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to update username.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password.");
        }
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
}

    @PutMapping("/updatepassword/{username}")
    public ResponseEntity<String> updatePassword(
        @PathVariable String username,
        @RequestBody Map<String, String> request) {
    
    String password = request.get("password");
    String newPassword = request.get("newpassword");
        if (myService.userExist(username).isSuccess()) {
            if (myService.authenticate(username, password)) {
                if (myService.updatePassword(username, newPassword)) {
                    return ResponseEntity.ok("Password updated successfuly.");
                } else {

                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password could not be updated!");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Existing password is not correct!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user exists!");
        }

    }
   

    @PutMapping("/updateemail/{id}")
    public ResponseEntity<String> updateEmail(@PathVariable String id, @RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");
        String newEmail = request.get("newemail");

        if (myService.userExist(username).isSuccess()) {
            if (myService.authenticate(username, password)) {
                if (myService.updateEmail(username, newEmail)) {
                    return ResponseEntity.ok("Email updated successfuly.");
                } else {

                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email could not be updated!");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Existing password is not correct!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user exists!");
        }

    }
}
