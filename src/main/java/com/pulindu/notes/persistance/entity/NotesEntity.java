package com.pulindu.notes.persistance.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="notes_seq_generator")
    @SequenceGenerator(name="notes_seq_generator", sequenceName = "notes_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "title")
    private String title;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    @Column(name = "date_created")
    private Timestamp dateCreated;

}
