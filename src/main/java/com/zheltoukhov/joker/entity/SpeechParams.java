package com.zheltoukhov.joker.entity;

import com.zheltoukhov.joker.speech.params.yandex.VoiceEmotion;
import com.zheltoukhov.joker.speech.params.yandex.VoiceLang;
import com.zheltoukhov.joker.speech.params.yandex.VoiceSpeaker;
import com.zheltoukhov.joker.speech.params.yandex.VoiceSpeed;

/**
 * Created by Maksim on 04.11.2017.
 */
public class SpeechParams {
    private String speaker = VoiceSpeaker.ZAHAR.getValue();
    private String lang = VoiceLang.RU.getValue();
    private String emotion = VoiceEmotion.NORMAL.getValue();
    private String speed = VoiceSpeed.NORMAL.getValue();

    public SpeechParams(String speaker, String lang, String emotion, String speed) {
        this.speaker = speaker;
        this.lang = lang;
        this.emotion = emotion;
        this.speed = speed;
    }

    public SpeechParams() {
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
