package com.zheltoukhov.joker.telegram.keyboards.settings;

import com.zheltoukhov.joker.entity.SpeechParams;
import com.zheltoukhov.joker.speech.params.yandex.VoiceParam;
import com.zheltoukhov.joker.speech.params.yandex.VoiceSpeaker;

import java.util.Map;

/**
 * Created by Maksim on 12.11.2017.
 */
public class SpeakerSettingsKeyboard extends AbstractSettingsKeyboard {

    @Override
    protected Map<String, ? extends VoiceParam> getParams() {
        return VoiceSpeaker.getValues();
    }
}
