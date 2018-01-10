package com.zheltoukhov.joker.telegram.actions.settings;

import com.zheltoukhov.joker.entity.SpeechParams;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.telegram.CommonAbstractAction;
import com.zheltoukhov.joker.telegram.actions.JokerAction;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.function.Consumer;

import static com.zheltoukhov.joker.telegram.actions.Actions.BUTTON_ACTION_SPLITTER;

/**
 * Created by Maksim on 13.11.2017.
 */
public abstract class AbstractSettingsAction extends CommonAbstractAction implements JokerAction {
    @Override
    public void execute(User user, CallbackQuery callbackQuery, Consumer<PartialBotApiMethod> botCallback) {
        long chatId = user.getChatId();
        String[] parts = callbackQuery.getData().split(BUTTON_ACTION_SPLITTER);
        String settingValue = parts[1];

        SpeechParams speechParams = getSpeechParams(user, settingValue);
        userService.setSpeechParams(user, speechParams);

        InlineKeyboardMarkup markup = getMarkup(settingValue);

        botCallback.accept(new EditMessageText()
                .setText(callbackQuery.getMessage().getText())
                .setChatId(chatId)
                .setReplyMarkup(markup)
                .setMessageId(callbackQuery.getMessage().getMessageId())
        );
    }

    protected abstract InlineKeyboardMarkup getMarkup(String settingValue);

    protected abstract SpeechParams getSpeechParams(User user, String settingValue);
}
