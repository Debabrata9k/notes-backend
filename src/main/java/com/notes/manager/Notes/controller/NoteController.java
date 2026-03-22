package com.notes.manager.Notes.controller;

import com.notes.manager.Notes.dto.AddNoteDto;
import com.notes.manager.Notes.dto.NoteDto;
import com.notes.manager.Notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/notes")
    public ResponseEntity<List<NoteDto>> getAllNote() {
        return ResponseEntity.ok(noteService.getNotes());
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Long id){
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteDto> createNewNote(@RequestBody AddNoteDto addNoteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNewNote(addNoteDto));
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<NoteDto> deleteANote(@PathVariable Long id){
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable Long id, @RequestBody AddNoteDto addNoteDto) {
        return ResponseEntity.ok(noteService.updateNote(id, addNoteDto));
    }
}
