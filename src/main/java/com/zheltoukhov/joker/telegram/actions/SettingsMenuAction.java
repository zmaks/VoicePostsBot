package com.zheltoukhov.joker.telegram.actions;

import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.entity.SpeechParams;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.telegram.CommonAbstractAction;
import com.zheltoukhov.joker.telegram.keyboards.settings.EmotionSettingsKeyboard;
import com.zheltoukhov.joker.telegram.keyboards.settings.LangSettingsKeyboard;
import com.zheltoukhov.joker.telegram.keyboards.settings.SpeakerSettingsKeyboard;
import com.zheltoukhov.joker.telegram.keyboards.settings.SpeedSettingsKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.function.Consumer;

import static com.zheltoukhov.joker.telegram.actions.Actions.*;

/**
 * Created by Maksim on 13.11.2017.
 */
@Component(SETTINGS_BUTTON_ACTION)
public class SettingsMenuAction extends CommonAbstractAction implements JokerAction {
    @Override
    public void execute(User user, CallbackQuery callbackQuery, Consumer<PartialBotApiMethod> botCallback) {
        String[] parts = callbackQuery.getData().split(BUTTON_ACTION_SPLITTER);
        String settingsAction = parts[1];
        SpeechParams params = user.getSpeechParams();

        String text = "";
        InlineKeyboardMarkup keyboardMarkup = null;
        switch (settingsAction) {
            case SPEAKER_SETTING_ACTION:
                text = MessageConstants.SPEAKER_SETTINGS_LABEL;
                keyboardMarkup = new SpeakerSettingsKeyboard().getSettingsKeyboard(params.getSpeaker());
                break;
            case LANG_SETTING_ACTION:
                text = MessageConstants.LANG_SETTINGS_LABEL;
                keyboardMarkup = new LangSettingsKeyboard().getSettingsKeyboard(params.getLang());
                break;
            case EMOTION_SETTING_ACTION:
                text = MessageConstants.EMOTION_SETTINGS_LABEL;
                keyboardMarkup = new EmotionSettingsKeyboard().getSettingsKeyboard(params.getEmotion());
                break;
            case SPEED_SETTING_ACTION:
                text = MessageConstants.SPEED_SETTINGS_LABEL;
                keyboardMarkup = new SpeedSettingsKeyboard().getSettingsKeyboard(params.getSpeed());
                break;
        }

        botCallback.accept(
                new EditMessageText()
                .setChatId(user.getChatId())
                .setText(text)
                .setReplyMarkup(keyboardMarkup)
                .setMessageId(callbackQuery.getMessage().getMessageId())
        );
    }
}
