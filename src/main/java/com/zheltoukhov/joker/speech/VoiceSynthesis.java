package com.zheltoukhov.joker.speech;

import com.zheltoukhov.joker.speech.params.VoiceParameters;

import java.io.InputStream;

/**
 * Created by Maksim on 03.11.2017.
 */
public interface VoiceSynthesis {
    InputStream getVoice(VoiceParameters text) throws VoiceException;
}
