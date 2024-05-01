package com.pulindu.notes.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NoteRequest {

    @JsonProperty("MyNotes")
    private MyNotes myNotes;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MyNotes {
        @JsonProperty("Title")
        private String title;

        @JsonProperty("Note")
        private String note;
    }
}
