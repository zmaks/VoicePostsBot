package com.zheltoukhov.joker.telegram.actions;

import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.telegram.CommonAbstractAction;
import com.zheltoukhov.joker.telegram.keyboards.SettingsKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;

import java.util.function.Consumer;

import static com.zheltoukhov.joker.telegram.actions.Actions.BACK_TO_SETTINGS_BUTTON_ACTION;

/**
 * Created by Maksim on 13.11.2017.
 */
@Component(BACK_TO_SETTINGS_BUTTON_ACTION)
public class BackToSettingsAction extends CommonAbstractAction implements JokerAction {
    @Override
    public void execute(User user, CallbackQuery callbackQuery, Consumer<PartialBotApiMethod> botCallback) {
        botCallback.accept(
                new EditMessageText()
                .setText(MessageConstants.MAIN_MENU_SETTINGS_LABEL)
                .setMessageId(callbackQuery.getMessage().getMessageId())
                .setChatId(user.getChatId())
                .setReplyMarkup(new SettingsKeyboard(user.getSpeechParams()).getMarkup())
        );
    }
}
