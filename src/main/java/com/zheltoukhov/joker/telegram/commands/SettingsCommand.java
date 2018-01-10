package com.zheltoukhov.joker.telegram.commands;

import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.telegram.CommonAbstractAction;
import com.zheltoukhov.joker.telegram.keyboards.SettingsKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import java.util.function.Consumer;

import static com.zheltoukhov.joker.telegram.commands.Commands.SETTING_COMMAND;

/**
 * Created by Maksim on 13.11.2017.
 */
@Component(SETTING_COMMAND)
public class SettingsCommand extends CommonAbstractAction implements JokerCommand {

    @Override
    public void execute(User user, Message message, Consumer<PartialBotApiMethod> botCallback) {
        if (user == null) {
            botCallback.accept(simpleTextResponse(MessageConstants.INCORRECT_START, user.getChatId()));
            return;
        }

        SendMessage sendMessage = new SendMessage()
                .setChatId(user.getChatId())
                .setText(MessageConstants.MAIN_MENU_SETTINGS_LABEL)
                .setReplyMarkup(new SettingsKeyboard(user.getSpeechParams()).getMarkup());
        botCallback.accept(sendMessage);
    }
}
