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

}
