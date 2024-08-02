package com.myspringproject.my_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myspringproject.my_api.entities.ToDoListEntity;
import com.myspringproject.my_api.repositories.ToDoRepository;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    //@Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
    
    public ToDoListEntity addToDo(ToDoListEntity todo){
        return toDoRepository.save(todo);
   }
     public List<ToDoListEntity> getAllData() {
        return toDoRepository.findAll();
    }
}
