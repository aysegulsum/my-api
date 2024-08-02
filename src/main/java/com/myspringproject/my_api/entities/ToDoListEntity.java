package com.myspringproject.my_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ToDoList")
public class ToDoListEntity {

    @Id
    @Column(name= "taskId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name ="isStarted")
    private boolean  isStarted;

    @Column(name ="isCompleted")
    private boolean  isCompleted;

    public ToDoListEntity() {
    }

    public ToDoListEntity(long id, String title, String description, boolean isStarted, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isStarted = isStarted;
        this.isCompleted = isCompleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    
}
