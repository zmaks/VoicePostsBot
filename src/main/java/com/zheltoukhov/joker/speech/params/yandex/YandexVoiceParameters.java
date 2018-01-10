package com.zheltoukhov.joker.speech.params.yandex;

import com.zheltoukhov.joker.entity.SpeechParams;
import com.zheltoukhov.joker.speech.params.VoiceParameters;

/**
 * Created by Maksim on 03.11.2017.
 */
public class YandexVoiceParameters implements VoiceParameters {
    private VoiceText voiceText = new VoiceText();
    private VoiceEmotion voiceEmotion = VoiceEmotion.NORMAL;
    private VoiceLang voiceLang = VoiceLang.RU;
    private VoiceSpeaker voiceSpeaker = VoiceSpeaker.ZAHAR;
    private VoiceSpeed voiceSpeed = VoiceSpeed.NORMAL;

    public YandexVoiceParameters(SpeechParams speechParams) {
        this.voiceEmotion = VoiceEmotion.getByValue(speechParams.getEmotion());
        this.voiceLang = VoiceLang.getByValue(speechParams.getLang());
        this.voiceSpeaker = VoiceSpeaker.getByValue(speechParams.getSpeaker());
        this.voiceSpeed = VoiceSpeed.getByValue(speechParams.getSpeed());
    }

    public YandexVoiceParameters() {
    }

    @Override
    public String getHttpParamsString() {
        return paramsAppender(
                voiceEmotion,
                voiceLang,
                voiceSpeaker,
                voiceSpeed,
                voiceText
        );
    }

    private String paramsAppender(VoiceParam... params) {
        StringBuilder builder = new StringBuilder();
        for (VoiceParam p : params) {
             builder.append("&")
                     .append(p.getName())
                     .append("=")
                     .append(p.getValue());
        }
        return builder.toString();
    }

    public static class Builder {
        private YandexVoiceParameters instance;

        public Builder() {
            this.instance = new YandexVoiceParameters();
        }

        public Builder text(VoiceText text) {
            if (text != null) instance.voiceText = text;
            return this;
        }

        public Builder lang(VoiceLang lang) {
            if (lang != null) instance.voiceLang = lang;
            return this;
        }

        public Builder speaker(VoiceSpeaker speaker) {
            if (speaker != null) instance.voiceSpeaker = speaker;
            return this;
        }

        public Builder emotion(VoiceEmotion emotion) {
            if (emotion != null) instance.voiceEmotion = emotion;
            return this;
        }

        public Builder speed(VoiceSpeed speed) {
            if (speed != null) instance.voiceSpeed = speed;
            return this;
        }

        public YandexVoiceParameters build() {
            return instance;
        }
    }

    public VoiceText getVoiceText() {
        return voiceText;
    }

    public VoiceLang getVoiceLang() {
        return voiceLang;
    }

    public VoiceSpeaker getVoiceSpeaker() {
        return voiceSpeaker;
    }

    public VoiceSpeed getVoiceSpeed() {
        return voiceSpeed;
    }

    public VoiceEmotion getVoiceEmotion() {
        return voiceEmotion;
    }

    public YandexVoiceParameters setVoiceText(VoiceText voiceText) {
        this.voiceText = voiceText;
        return this;
    }
}
