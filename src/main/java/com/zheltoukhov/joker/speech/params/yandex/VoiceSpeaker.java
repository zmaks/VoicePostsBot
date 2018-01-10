package com.zheltoukhov.joker.speech.params.yandex;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim on 03.11.2017.
 */
public enum VoiceSpeaker implements VoiceParam {
    ZAHAR("zahar", "Захар"),
    EREMA("ermil", "Ерёма"),
    JANE("jane", "Жанна"),
    OKSANA("oksana", "Оксана"),
    ALYSS("alyss", "Алиса"),
    MASHA("omazh", "Маша");

    private final String name = "speaker";
    private static final Map<String, VoiceSpeaker> values = new HashMap<>();
    private String value;
    private String settingName;


    static {
        for (VoiceSpeaker vf : VoiceSpeaker.values())
            values.put(vf.value, vf);
    }

    VoiceSpeaker(String value, String settingName) {
        this.value = value;
        this.settingName = settingName;
    }

    public static VoiceSpeaker getByValue(String value) {
        return values.get(value);
    }

    public static Map<String, VoiceSpeaker> getValues() {
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
