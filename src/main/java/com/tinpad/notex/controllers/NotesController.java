package com.tinpad.notex.controllers;

import com.tinpad.notex.entities.Notes;
import com.tinpad.notex.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotesController {

    @Autowired
    NotesRepository notesRepository;

    @GetMapping("/notes")
    public List<Notes> getNote(@RequestParam(name = "id", required = false) Long id) {
        if(id != null) {
            return Arrays.asList(notesRepository.findById(id).get());
        }
        return notesRepository.findAll();
    }

    @PostMapping("/notes")
    public Notes addNote(@RequestBody Notes note) {
        notesRepository.save(note);
        return note;
    }

    @DeleteMapping("/notes")
    public void deleteNote(@RequestParam(name = "id", required = false) Long id) {
        if(id != null) {
            notesRepository.deleteById(id);
        } else {
            notesRepository.deleteAll();
        }
    }

    @PutMapping("/notes")
    public Notes updateNote(@RequestBody Notes note) {
        notesRepository.saveAndFlush(note);
        return note;
    }

}
