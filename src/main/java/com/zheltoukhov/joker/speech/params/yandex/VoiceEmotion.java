package com.zheltoukhov.joker.speech.params.yandex;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim on 03.11.2017.
 */
public enum VoiceEmotion implements VoiceParam {
    GOOD("good", "Добряк"),
    EVIL("evil", "Злой"),
    NORMAL("neutral", "Обычный");

    private final String name = "emotion";
    private static final Map<String, VoiceEmotion> values = new HashMap<>();
    private String value;
    private String settingName;

    static {
        for (VoiceEmotion vf : VoiceEmotion.values())
            values.put(vf.value, vf);
    }

    VoiceEmotion(String value, String settingName) {
        this.value = value;
        this.settingName = settingName;
    }

    public static VoiceEmotion getByValue(String value) {
        return values.get(value);
    }

    public static Map<String, VoiceEmotion> getValues() {
        return values;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getSettingName() {
        return settingName;
    }
}
