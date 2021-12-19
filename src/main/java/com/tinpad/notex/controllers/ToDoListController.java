package com.tinpad.notex.controllers;

import com.tinpad.notex.entities.ToDoList;
import com.tinpad.notex.repositories.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ToDoListController {

    @Autowired
    ToDoListRepository toDoListRepository;

    @GetMapping("/todo")
    public List<ToDoList> getTodo(@RequestParam(name = "id", required = false) Long id) {
        if(id != null) {
            return Arrays.asList(toDoListRepository.findById(id).get());
        }
        return toDoListRepository.findAll();
    }

    @PostMapping("/todo")
    public ToDoList addTodo(@RequestBody ToDoList toDoList) {
        toDoListRepository.save(toDoList);
        return toDoList;
    }

    @DeleteMapping("/todo")
    public void deleteTodo(@RequestParam(name = "id", required = false) Long id) {
        if(id != null) {
            toDoListRepository.deleteById(id);
        } else {
            toDoListRepository.deleteAll();
        }
    }

    @PutMapping("/todo")
    public ToDoList updateTodo(@RequestBody ToDoList toDoList) {
        toDoListRepository.saveAndFlush(toDoList);
        return toDoList;
    }

}
