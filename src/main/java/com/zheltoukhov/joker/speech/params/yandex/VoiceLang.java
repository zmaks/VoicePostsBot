package com.zheltoukhov.joker.speech.params.yandex;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim on 03.11.2017.
 */
public enum VoiceLang implements VoiceParam {
    RU("ru-RU", "Русский"),
    UA("uk-UK", "Украинский"),
    EN("en-US", "Английский");

    private final String name = "lang";
    private static final Map<String, VoiceLang> values = new HashMap<>();
    private String value;
    private String settingName;


    static {
        for (VoiceLang vf : VoiceLang.values())
            values.put(vf.value, vf);
    }

    VoiceLang(String value, String settingName) {
        this.value = value;
        this.settingName = settingName;
    }

    public static VoiceLang getByValue(String value) {
        return values.get(value);
    }

    public static Map<String, VoiceLang> getValues() {
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
