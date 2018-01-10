package com.zheltoukhov.joker;

/**
 * Created by Maksim on 22.10.2017.
 */
public class JokeException extends Exception {
    public JokeException() {
    }

    public JokeException(String message) {
        super(message);
    }

    public JokeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JokeException(Throwable cause) {
        super(cause);
    }
}
