package com.zheltoukhov.joker.telegram.filters;

import com.zheltoukhov.joker.entity.User;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;

import java.util.function.Consumer;

/**
 * Created by Maksim on 19.11.2017.
 */
public interface JokerFilter {
    boolean execute(User user, Consumer<PartialBotApiMethod> botCallback);
}
