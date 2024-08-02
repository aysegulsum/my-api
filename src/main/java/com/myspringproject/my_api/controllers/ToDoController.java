package com.myspringproject.my_api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<String> addToDO(  @RequestParam long id,
                                            @RequestParam String title, 
                                            @RequestParam String description, 
                                            @RequestParam boolean isStarted,
                                            @RequestParam boolean isCompleted) {
        // UserEntity sınıfının bir nesnesini oluşturuyoruz
        ToDoListEntity todo = new ToDoListEntity(id, title, description, isStarted, isCompleted);

        // Servis sınıfını kullanarak kişiyi ekliyoruz
        myService.addToDo(todo);

        // Başarılı bir yanıt döndürüyoruz
        return ResponseEntity.ok("ToDo item added successfully.");
    }

      @GetMapping("/list")
    public List<ToDoListEntity> getAllEntities() {
        return myService.getAllData();
    }
}



