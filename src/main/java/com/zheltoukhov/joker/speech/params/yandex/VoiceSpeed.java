package com.zheltoukhov.joker.speech.params.yandex;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Maksim on 04.11.2017.
 */
public enum VoiceSpeed implements VoiceParam {
    VERY_SLOW("0.3", "Очень медленно"),
    SLOW("0.5", "Медленно"),
    SLOWLY("0.8", "Помедленнее"),
    NORMAL("1", "Нормально"),
    FASTER("1.2", "Побыстрее"),
    FAST("1.6", "Быстро"),
    VERY_FAST("2", "Очень быстро");

    private static final Map<String, VoiceSpeed> values = new LinkedHashMap<>();
    private final String name = "speed";
    private String value = "1.0";
    private String settingName;

    static {
        for (VoiceSpeed vf : VoiceSpeed.values())
            values.put(vf.value, vf);
    }

    VoiceSpeed(String value, String settingName) {
        this.value = value;
        this.settingName = settingName;
    }

    public static VoiceSpeed getByValue(String value) {
        return values.get(value);
    }

    public static Map<String, VoiceSpeed> getValues() {
        return values;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getSettingName() {
        return settingName;
    }
}
