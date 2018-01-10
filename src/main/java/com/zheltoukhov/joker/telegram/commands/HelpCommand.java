package com.zheltoukhov.joker.telegram.commands;

import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.telegram.CommonAbstractAction;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.Message;

import java.util.function.Consumer;

/**
 * Created by Maksim on 25.11.2017.
 */
@Component(Commands.HELP_COMMAND)
public class HelpCommand extends CommonAbstractAction implements JokerCommand {
    @Override
    public void execute(User user, Message message, Consumer<PartialBotApiMethod> botCallback) {
        botCallback.accept(
                simpleTextResponse(MessageConstants.HELP_TEXT, message.getChatId())
        );
    }
}
