package com.music.tagger.api.thirdparty.musixmatch;

public class MusixMatchException extends Exception {

    public MusixMatchException(String message) {
        super(message);
    }

    public MusixMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
