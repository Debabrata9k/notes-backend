package com.notes.manager.Notes.service;

import com.notes.manager.Notes.dto.AddNoteDto;
import com.notes.manager.Notes.dto.NoteDto;

import java.net.URI;
import java.util.List;

public interface NoteService {
    List<NoteDto> getNotes();

    NoteDto getNoteById(Long id);

    NoteDto createNewNote(AddNoteDto addNoteDto);

    void deleteNoteById(Long id);

    NoteDto updateNote(Long id, AddNoteDto addNoteDto);
}
