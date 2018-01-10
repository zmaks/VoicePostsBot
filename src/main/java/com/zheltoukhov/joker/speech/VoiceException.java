package com.zheltoukhov.joker.speech;

/**
 * Created by Maksim on 03.11.2017.
 */
public class VoiceException extends Exception {

    public VoiceException(String message) {
        super(message);
    }

    public VoiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoiceException(Throwable cause) {
        super(cause);
    }
}
