package com.music.tagger.exceptions;

import java.util.NoSuchElementException;

/**
 * Thrown by various accessor methods to indicate that the element being requested
 * does not exist.
 */
public class UserNotFoundException extends Exception {

    /**
     * Constructs a {@code UserNotFoundException} with {@code null}
     * as its error message string.
     */
    public UserNotFoundException() {
        super();
    }

    /**
     * Constructs a {@code UserNotFoundException}, saving a reference
     * to the error message string {@code s} for later retrieval by the
     * {@code getMessage} method.
     *
     * @param   s   the detail message.
     */
    public UserNotFoundException(String s) {
        super(s);
    }
}
