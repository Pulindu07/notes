package com.pulindu.notes.service;

import com.pulindu.notes.model.request.NoteRequest;
import com.pulindu.notes.model.response.AllNoteResponse;
import com.pulindu.notes.model.response.NoteResponse;

public interface Notes {
    NoteResponse saveNote(String userId, NoteRequest note);

    NoteResponse update(String userId, Long noteId, NoteRequest request);

    NoteResponse changeStatus(String userId, Long noteId, String status);

    AllNoteResponse getNotes(String userId, String status);

    NoteResponse deleteNote(String userId, Long noteId);
}
