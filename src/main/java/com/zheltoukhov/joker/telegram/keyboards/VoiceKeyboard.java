package com.zheltoukhov.joker.telegram.keyboards;

import com.zheltoukhov.joker.helpers.MessageConstants;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.zheltoukhov.joker.telegram.actions.Actions.REPEAT_BUTTON_ACTION;
import static com.zheltoukhov.joker.telegram.actions.Actions.SHOW_BUTTON_ACTION;

/**
 * Created by Maksim on 13.11.2017.
 */
public class VoiceKeyboard extends AbstractKeyboard {
    private boolean show;
    private String postId;

    public VoiceKeyboard(boolean show, String postId) {
        this.show = show;
        this.postId = postId;
    }

    @Override
    public InlineKeyboardMarkup getMarkup() {
        InlineKeyboardBuilder keyboardBuilder = new InlineKeyboardBuilder();

        keyboardBuilder
                .addButton(MessageConstants.REPEAT_BUTTON_LABEL, buildCallback(REPEAT_BUTTON_ACTION, postId));
        if (show) {
            keyboardBuilder
                    .newLine()
                    .addButton(MessageConstants.SHOW_TEXT_BUTTON_LABEL, buildCallback(SHOW_BUTTON_ACTION, postId));
        }

        return keyboardBuilder.build();
    }
}
