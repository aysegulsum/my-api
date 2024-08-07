package com.myspringproject.my_api.controllers;

import java.util.List;
import java.util.Map;

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

import com.myspringproject.my_api.entities.ToDoListEntity;
import com.myspringproject.my_api.services.ToDoService;

@RestController
@RequestMapping("/ToDo")
public class ToDoController {

    private final ToDoService myService;

    //@Autowired
    public ToDoController(ToDoService myService) {
        this.myService = myService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToDO(@RequestParam long id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam boolean isStarted,
            @RequestParam boolean isCompleted) {

        ToDoListEntity todo = new ToDoListEntity(id, title, description, isStarted, isCompleted);
        if(myService.addToDo(todo)){
               return ResponseEntity.ok("ToDo item added successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ToDo item could not added!");
        }
}

    @GetMapping("/list")
    public List<ToDoListEntity> getAllEntities() {
        return myService.getAllData();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable long id) {//, @RequestBody Map<String, String> request){
        
        if (myService.delete(id)) {
            return ResponseEntity.ok("Delete successful");
        } else {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ToDo could not be deleted!");
        }
    }

    @PutMapping("/updatestart/{id}")
    public ResponseEntity<String> updateStart(@PathVariable long id, @RequestBody Map<String, String> request) {
        Boolean bool = Boolean.valueOf(request.get("str"));
        if (myService.updateStart(id, bool)) {
            return ResponseEntity.ok("Updated successfuly");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ToDo could not be updated!");
        }

    }

    @PutMapping("/updatecomplete/{id}")
    public ResponseEntity<String> updateComplete(@PathVariable long id, @RequestBody Map<String, String> request) {
        Boolean bool = Boolean.valueOf(request.get("str"));
        if (myService.updateComplete(id, bool)) {
            return ResponseEntity.ok("Updated successfuly");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ToDo could not be updated!");
        }
    }

    @GetMapping("/print")
    public String getCatagories() {
        Map<String, List<ToDoListEntity>> groupedTodos = myService.group();
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, List<ToDoListEntity>> entry : groupedTodos.entrySet()) {
            result.append("Title: ").append(entry.getKey()).append("\n");
            for (ToDoListEntity todo : entry.getValue()) {
                result.append("  Description: ").append(todo.getDescription()).append("\n");
            }
            result.append("\n");
        }
        return result.toString();
    }
}


