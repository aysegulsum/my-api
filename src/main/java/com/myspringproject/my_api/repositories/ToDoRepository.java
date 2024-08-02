package com.myspringproject.my_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myspringproject.my_api.entities.ToDoListEntity;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoListEntity, Long>{

}
