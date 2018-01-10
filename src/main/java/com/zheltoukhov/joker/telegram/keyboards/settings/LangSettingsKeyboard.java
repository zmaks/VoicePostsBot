package com.zheltoukhov.joker.telegram.keyboards.settings;

import com.zheltoukhov.joker.speech.params.yandex.VoiceLang;
import com.zheltoukhov.joker.speech.params.yandex.VoiceParam;

import java.util.Map;

/**
 * Created by Maksim on 12.11.2017.
 */
public class LangSettingsKeyboard extends AbstractSettingsKeyboard {

    @Override
    protected Map<String, ? extends VoiceParam> getParams() {
        return VoiceLang.getValues();
    }
}
