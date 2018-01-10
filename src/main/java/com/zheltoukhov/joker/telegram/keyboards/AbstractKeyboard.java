package com.zheltoukhov.joker.telegram.keyboards;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.zheltoukhov.joker.telegram.actions.Actions.BUTTON_ACTION_SPLITTER;

/**
 * Created by Maksim on 13.11.2017.
 */
public abstract class AbstractKeyboard {

    public abstract InlineKeyboardMarkup getMarkup();

    protected String buildCallback(String... parts) {
        return Arrays.stream(parts).collect(Collectors.joining(BUTTON_ACTION_SPLITTER));
    }
}
