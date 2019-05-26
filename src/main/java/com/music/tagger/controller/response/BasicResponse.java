package com.music.tagger.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicResponse {

    private String message;
    private String error;

    public BasicResponse(String message) {
        this.message = message;
    }

    public BasicResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }
}
