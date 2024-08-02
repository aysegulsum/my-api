package com.myspringproject.my_api.services;

import java.util.List;

import com.myspringproject.my_api.entities.UserEntity;
import com.myspringproject.my_api.repositories.UserRepository;


@org.springframework.stereotype.Service
public class UserService {

    final private UserRepository userRepository; 
   

    //@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean authenticate(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
    public boolean cangetmail(String username){
        UserEntity user = userRepository.findByUsername(username);
        return user != null;
      
    }
    public String getemail(String username){
        UserEntity user = userRepository.findByUsername(username);
        return user.getEmail();
    }

    public List<UserEntity> getAllData() {
        return userRepository.findAll();
    }

    public UserEntity addPerson(UserEntity person) {
        return userRepository.save(person);
    }

    
    }

