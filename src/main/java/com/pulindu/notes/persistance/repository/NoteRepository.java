package com.pulindu.notes.persistance.repository;

import com.pulindu.notes.persistance.entity.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository  extends JpaRepository<NotesEntity, Long> {
    NotesEntity findOneByUserIdAndId(String userId, long id);
    NotesEntity findOneByUserIdAndStatusAndId(String userId, String status, long id);
    List<NotesEntity> findAllByUserIdAndStatus(String userId, String status);
}
