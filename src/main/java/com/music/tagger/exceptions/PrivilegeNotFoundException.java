package com.music.tagger.exceptions;

import java.util.NoSuchElementException;

/**
 * Thrown by various accessor methods to indicate that the element being requested
 * does not exist.
 */
public class PrivilegeNotFoundException extends NoSuchElementException {

    /**
     * Constructs a {@code PrivilegeNotFoundException} with {@code null}
     * as its error message string.
     */
    public PrivilegeNotFoundException() {
        super();
    }

    /**
     * Constructs a {@code PrivilegeNotFoundException}, saving a reference
     * to the error message string {@code s} for later retrieval by the
     * {@code getMessage} method.
     *
     * @param   s   the detail message.
     */
    public PrivilegeNotFoundException(String s) {
        super(s);
    }
}
