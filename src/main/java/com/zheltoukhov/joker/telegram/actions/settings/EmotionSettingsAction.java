package com.zheltoukhov.joker.telegram.actions.settings;

import com.zheltoukhov.joker.entity.SpeechParams;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.telegram.keyboards.settings.EmotionSettingsKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.zheltoukhov.joker.telegram.actions.Actions.EMOTION_SETTING_ACTION;

/**
 * Created by Maksim on 13.11.2017.
 */
@Component(EMOTION_SETTING_ACTION)
public class EmotionSettingsAction extends AbstractSettingsAction {
    @Override
    protected InlineKeyboardMarkup getMarkup(String settingValue) {
        return new EmotionSettingsKeyboard().getSettingsKeyboard(settingValue);
    }

    @Override
    protected SpeechParams getSpeechParams(User user, String settingValue) {
        SpeechParams params = user.getSpeechParams();
        params.setEmotion(settingValue);
        return params;
    }
}
