package com.myspringproject.my_api.controllers;

import java.util.List;

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

    @PutMapping("path/{userName}")
    public ResponseEntity<String> updateUserName(@PathVariable String username, @RequestBody String password, @RequestBody String newName) {
        if (myService.userExist(username).isSuccess()) {
            if (myService.authenticate(username, password)) {
                if (myService.updateUserName(username, newName)) {
                    return ResponseEntity.ok("Username updated successfuly.");
                } else {

                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username could not be updated!");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password is not correct!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user exists!");
        }

    }

    /*
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userDetails) {
        try {
            UserEntity updatedUser = userService.updateUser(id, userDetails);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
     */
    @PutMapping("/updatepassword")
    public ResponseEntity<String> updatePassword(@RequestBody String username, @RequestBody String password, @RequestBody String newPassword) {
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
    @PutMapping("path/{id}")
    public ResponseEntity<String>  putMethodName(@PathVariable String id, @RequestBody String username, @RequestBody String password, @RequestBody String newPassword) {
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

    @PutMapping("/updateemail")
    public ResponseEntity<String> updateEmail(@RequestBody String username, @RequestBody String password, @RequestBody String newEmail) {
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
