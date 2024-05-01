package com.pulindu.notes.controller;

import com.pulindu.notes.model.request.NoteRequest;
import com.pulindu.notes.model.response.AllNoteResponse;
import com.pulindu.notes.model.response.NoteResponse;
import com.pulindu.notes.service.Notes;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class notesController {

    @Autowired
    Notes notes;

    @PostMapping("/save/{userId}")
    public NoteResponse addNotes(@PathVariable String userId, @RequestBody NoteRequest request){
        return notes.saveNote(userId,request);
    }
    @PutMapping("/update/{userId}/{noteId}")
    public NoteResponse updateNote(@PathVariable String userId,@PathVariable Long noteId, @RequestBody NoteRequest request){
        return notes.update(userId, noteId,request);
    }
    @PutMapping("/change-status/{userId}/{noteId}")
    public NoteResponse StatusChangeNote(@PathVariable String userId, @PathVariable Long noteId, @PathParam("status") String status){
        return notes.changeStatus(userId, noteId, status);
    }

    @GetMapping("{userId}")
    public AllNoteResponse getAllNotes(@PathVariable String userId, @PathParam("status") String status){
        return notes.getNotes(userId, status);
    }
    @DeleteMapping ("/delete/{userId}/{noteId}")
    public NoteResponse deleteNotes(@PathVariable String userId, @PathVariable Long noteId){
        return notes.deleteNote(userId, noteId);
    }

}
