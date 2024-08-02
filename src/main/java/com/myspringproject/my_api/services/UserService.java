package com.myspringproject.my_api.services;

import java.util.Collections;
import java.util.List;

import com.myspringproject.my_api.entities.UserEntity;
import com.myspringproject.my_api.repositories.UserRepository;
import com.myspringproject.my_api.responses.OperationResult;

@org.springframework.stereotype.Service
public class UserService {

    final private UserRepository userRepository;

    //@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public OperationResult userExist(String username, String password) {
        try {
            UserEntity user = userRepository.findByUsername(username);
            return new OperationResult(user != null, user != null ? "User exists" : "User not exists");
        } catch (Exception ex) {
            return new OperationResult(false, ex.getMessage());
        }
    }

    public boolean authenticate(String username, String password) {
        try {
            UserEntity user = userRepository.findByUsername(username);
            return user.getPassword().equals(password);
        } catch (Exception ex) {
            return false;
        }
    }

    public OperationResult addPerson(UserEntity person) {
        try {
            userRepository.save(person);
            return new OperationResult(true, "Person added successfully.");
        } catch (Exception ex) {
            return new OperationResult(false, "Person could not be added!: " + ex.getMessage());
        }

    }

    public List<UserEntity> getAllData() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    //extra
    public String getemail(String username) {
        try {
            UserEntity user = userRepository.findByUsername(username);
        return user.getEmail();
        } catch (Exception e) {
            return "could not be taken";
        }
        
    }

}
