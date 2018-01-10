package com.zheltoukhov.joker.telegram.keyboards;

import com.zheltoukhov.joker.entity.SpeechParams;
import com.zheltoukhov.joker.speech.params.yandex.VoiceParam;
import com.zheltoukhov.joker.speech.params.yandex.YandexVoiceParameters;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.zheltoukhov.joker.helpers.MessageConstants.*;
import static com.zheltoukhov.joker.telegram.actions.Actions.*;

/**
 * Created by Maksim on 13.11.2017.
 */
public class SettingsKeyboard extends AbstractKeyboard {

    private YandexVoiceParameters speechParams;

    public SettingsKeyboard(SpeechParams speechParams) {
        this.speechParams = new YandexVoiceParameters(speechParams);
    }

    @Override
    public InlineKeyboardMarkup getMarkup() {
        return new InlineKeyboardBuilder()
                .addButton(getParamName(SPEAKER_LABEL, speechParams.getVoiceSpeaker()),
                        buildCallback(SETTINGS_BUTTON_ACTION, SPEAKER_SETTING_ACTION))
                .newLine()
                .addButton(getParamName(LANG_LABEL, speechParams.getVoiceLang()),
                        buildCallback(SETTINGS_BUTTON_ACTION, LANG_SETTING_ACTION))
                .newLine()
                .addButton(getParamName(SPEED_LABEL, speechParams.getVoiceSpeed()),
                        buildCallback(SETTINGS_BUTTON_ACTION, SPEED_SETTING_ACTION))
                .newLine()
                .addButton(getParamName(EMOTION_LABEL, speechParams.getVoiceEmotion()),
                        buildCallback(SETTINGS_BUTTON_ACTION, EMOTION_SETTING_ACTION))
                .build();
    }

    private String getParamName(String label, VoiceParam voiceParam) {
        return String.format(MAIN_MENU_SETTING_BUTTON_PATTERN, label, voiceParam.getSettingName());
    }
}
