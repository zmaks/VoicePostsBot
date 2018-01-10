package com.zheltoukhov.joker.telegram.commands;

import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.telegram.CommonAbstractAction;
import com.zheltoukhov.joker.telegram.Utils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.Message;

import java.util.function.Consumer;

import static com.zheltoukhov.joker.telegram.commands.Commands.START_COMMAND;

/**
 * Created by Maksim on 04.11.2017.
 */
@Component(START_COMMAND)
public class StartCommand extends CommonAbstractAction implements JokerCommand {

    @Override
    public void execute(User user, Message message, Consumer<PartialBotApiMethod> botCallback) {
        long chatId = message.getChatId();
        if (user == null) {
            user = new User(chatId);
            String[] parts = message.getText().split(" ");
            if (parts.length == 2) {
                user.setContext(parts[1]);
            }
        }
        user.setName(Utils.getTelegramUserName(message.getFrom()));
        user.setViewed(null);
        userService.addUser(user);

        botCallback.accept(simpleTextResponse(MessageConstants.START_COMMAND_TEXT, chatId));
    }

}
