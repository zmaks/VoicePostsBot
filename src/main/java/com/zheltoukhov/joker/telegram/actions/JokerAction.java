package com.zheltoukhov.joker.telegram.actions;

import com.zheltoukhov.joker.entity.User;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.CallbackQuery;

import java.util.function.Consumer;

/**
 * Created by Maksim on 11.11.2017.
 */
public interface JokerAction {
    void execute(User user, CallbackQuery callbackQuery, Consumer<PartialBotApiMethod> botCallback);
}
