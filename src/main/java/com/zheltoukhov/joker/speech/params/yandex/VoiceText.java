package com.zheltoukhov.joker.speech.params.yandex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Maksim on 04.11.2017.
 */
public class VoiceText implements VoiceParam {
    private final String name = "text";
    private String text = "";

    public VoiceText() {
    }

    public VoiceText(String text) {
        this.text = text;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String getSettingName() {
        return "Текст";
    }
}
