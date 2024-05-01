package com.pulindu.notes.service;

import com.pulindu.notes.Constants;
import com.pulindu.notes.model.request.NoteRequest;
import com.pulindu.notes.model.response.AllNoteResponse;
import com.pulindu.notes.model.response.NoteResponse;
import com.pulindu.notes.persistance.entity.NotesEntity;
import com.pulindu.notes.persistance.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NotesImpl implements Notes {

    Logger logger = LoggerFactory.getLogger(NotesImpl.class);

    @Autowired
    NoteRepository noteRepository;

    @Override
    public NoteResponse saveNote(String userId, NoteRequest note) {
        logger.info("Started notes service...");
        logger.info("request - {}", note);

        NotesEntity notesEntity = NotesEntity.builder()
                .userId(userId)
                .title(note.getMyNotes().getTitle())
                .note(note.getMyNotes().getNote())
                .status(Constants.ACTIVE)
                .dateCreated(new Timestamp(new Date().getTime()))
                .build();

        try {
            noteRepository.save(notesEntity);

            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N00","Successful", notesEntity.getId(),userId, Constants.ACTIVE
                    )
            );
        }catch (Exception e){
            logger.error("Error saving note - {}", e.getMessage());

            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N500","Fail", null,userId, null
                    )
            );
        }
    }

    @Override
    public NoteResponse update(String userId, Long id, NoteRequest request) {
        NotesEntity note = noteRepository.findOneByUserIdAndId(userId, id);
        if (note == null){
            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N100",Constants.NOT_FOUND, null, userId, null
                    )
            );
        }
        note.setTitle(request.getMyNotes().getTitle());
        note.setNote(request.getMyNotes().getNote());
        try {
            noteRepository.save(note);
            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N00","Successful", note.getId(),userId, Constants.ACTIVE
                    )
            );
        }catch (Exception e){
            logger.error("Error updating note - {}", e.getMessage());
            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N500","Fail", null,userId, null
                    )
            );
        }
    }

    @Override
    public NoteResponse changeStatus(String userId, Long id, String status) {
        String queryStatus = Constants.ARCHIVE;
        if (status.equalsIgnoreCase(Constants.ARCHIVE)){
            queryStatus = Constants.ACTIVE;
        }
        NotesEntity note = noteRepository.findOneByUserIdAndStatusAndId(userId, queryStatus, id);
        if (note == null){
            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N100",queryStatus.concat(" ").concat(Constants.NOT_FOUND), null, userId, null
                    )
            );
        }
        note.setStatus(status);
        try {
            noteRepository.save(note);
            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N00","Successful", note.getId(),userId, Constants.ACTIVE
                    )
            );
        }catch (Exception e){
            logger.error("Error updating note - {}", e.getMessage());
            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N500","Fail", null,userId, null
                    )
            );
        }
    }

    @Override
    public AllNoteResponse getNotes(String userId, String status) {
        if (!status.equalsIgnoreCase(Constants.ACTIVE) && !status.equalsIgnoreCase(Constants.ARCHIVE)){
            return new AllNoteResponse(
                    new AllNoteResponse.MyNotes(
                            "N102",Constants.INVALID_STATUS, null
                    )
            );
        }
        List<NotesEntity> notes = noteRepository.findAllByUserIdAndStatus(userId, status);
        if (notes.isEmpty()){
            return new AllNoteResponse(
                    new AllNoteResponse.MyNotes(
                            "N100",Constants.NOT_FOUND, null
                    )
            );
        }
        ArrayList<AllNoteResponse.MyNotes.Notes> allNotes = new ArrayList<>();
        for (NotesEntity note:notes){
            AllNoteResponse.MyNotes.Notes tempNote = new AllNoteResponse.MyNotes.Notes();
            tempNote.setId(note.getId().toString());
            tempNote.setTitle(note.getTitle());
            tempNote.setNote(note.getNote());
            tempNote.setStatus(note.getStatus());
            allNotes.add(tempNote);
        }

        return new AllNoteResponse(
                new AllNoteResponse.MyNotes(
                        "N00", "Successful", allNotes
                )
        );
    }

    @Override
    public NoteResponse deleteNote(String userId, Long noteId) {
        NotesEntity note = noteRepository.findOneByUserIdAndId(userId, noteId);
        if (note == null){
            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N100",Constants.NOT_FOUND, null, userId, null
                    )
            );
        }
        try {
            noteRepository.delete(note);
            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N00","Successful", note.getId(),userId, null
                    )
            );
        }catch (Exception e){
            logger.error("Error updating note - {}", e.getMessage());
            return new NoteResponse(
                    new NoteResponse.MyNotes(
                            "N500","Fail", null,userId, null
                    )
            );
        }
    }
}
