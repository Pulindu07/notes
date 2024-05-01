package com.pulindu.notes.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pulindu.notes.persistance.entity.NotesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllNoteResponse {

    @JsonProperty("MyNotes")
    private MyNotes myNotes;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MyNotes {

        @JsonProperty("StatusCode")
        private String code;

        @JsonProperty("StatusMessage")
        private String message;

        @JsonProperty("Notes")
        private List<Notes> notes;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Notes{
            @JsonProperty("Id")
            private String id;
            @JsonProperty("Title")
            private String title;
            @JsonProperty("Note")
            private String note;
            @JsonProperty("Status")
            private String status;
        }

    }
}
