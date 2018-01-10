package com.zheltoukhov.joker.telegram.commands;

import com.zheltoukhov.joker.entity.User;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.Message;

import java.util.function.Consumer;

/**
 * Created by Maksim on 11.11.2017.
 */
public interface JokerCommand {
    void execute(User user, Message message, Consumer<PartialBotApiMethod> botCallback);
}
