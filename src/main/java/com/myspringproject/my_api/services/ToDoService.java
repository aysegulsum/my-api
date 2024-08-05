package com.myspringproject.my_api.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ToDoListEntity addToDo(ToDoListEntity todo) {
        return toDoRepository.save(todo);
    }

    public List<ToDoListEntity> getAllData() {
        return toDoRepository.findAll();
    }

    public boolean delete(long id) {
        try {
            ToDoListEntity todo = this.toDoRepository.findById(id);
            toDoRepository.deleteById(todo.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateStart(long id, boolean bool) {
        try {
            ToDoListEntity temp = this.toDoRepository.findById(id);
            temp.setStarted(bool);
            toDoRepository.save(temp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateComplete(long id, boolean bool) {
        try {
            ToDoListEntity temp = this.toDoRepository.findById(id);
            temp.setCompleted(bool);
            toDoRepository.save(temp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, List<ToDoListEntity>> group() {
        try {
            Map<String, List<ToDoListEntity>> groupedTodos = new HashMap<>();
            for (ToDoListEntity todo : toDoRepository.findAll()) {
                groupedTodos.computeIfAbsent(todo.getTitle(), k -> new ArrayList<>()).add(todo);
    
            }
            return groupedTodos;
        } catch (Exception e) {
            //e.printStackTrace(); 
            return new HashMap<>();
        }
       
    }

}
