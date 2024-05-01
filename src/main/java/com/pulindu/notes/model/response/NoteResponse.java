package com.pulindu.notes.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {

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

        @JsonProperty("NoteId")
        private Long id;

        @JsonProperty("UserId")
        private String userId;

        @JsonProperty("NoteStatus")
        private String status;

    }
}
