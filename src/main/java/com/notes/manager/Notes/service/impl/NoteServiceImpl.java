package com.notes.manager.Notes.service.impl;

import com.notes.manager.Notes.dto.AddNoteDto;
import com.notes.manager.Notes.dto.NoteDto;
import com.notes.manager.Notes.entity.Note;
import com.notes.manager.Notes.repository.NoteRepository;
import com.notes.manager.Notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<NoteDto> getNotes() {
        List<Note> notes = noteRepository.findAll();
        return notes
                .stream()
                .map(note -> modelMapper.map(note, NoteDto.class))
                .toList();
    }

    @Override
    public NoteDto getNoteById(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Notes Not Found..."));
        return modelMapper.map(note, NoteDto.class);
    }

    @Override
    public NoteDto createNewNote(AddNoteDto addNoteDto) {
        Note newNote = modelMapper.map(addNoteDto, Note.class);
        Note note = noteRepository.save(newNote);
        return modelMapper.map(note, NoteDto.class);
    }

    @Override
    public void deleteNoteById(Long id) {
        if (!noteRepository.existsById(id)){
            throw new IllegalArgumentException("Notes Does not Exits By Id: "+id);
        }
        noteRepository.deleteById(id);
    }

    @Override
    public NoteDto updateNote(Long id, AddNoteDto addNoteDto) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Note not found"));
        modelMapper.map(addNoteDto, note);
        note = noteRepository.save(note);
        return modelMapper.map(note, NoteDto.class);
    }

}
