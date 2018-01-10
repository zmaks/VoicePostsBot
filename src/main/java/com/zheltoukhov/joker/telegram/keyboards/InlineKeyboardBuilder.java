package com.zheltoukhov.joker.telegram.keyboards;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


public class InlineKeyboardBuilder {
    private String keyBoardName;
    List<List<InlineKeyboardButton>> keyboardLines;
    List<InlineKeyboardButton> currentLine;

    public InlineKeyboardBuilder() {
        keyboardLines = new ArrayList<>();
        currentLine = new ArrayList<>();
        keyboardLines.add(currentLine);
    }

    public InlineKeyboardBuilder addButton(String label, String callBack) {
        //String data = buildCallbackData(callBack);
        InlineKeyboardButton button = new InlineKeyboardButton()
                .setText(label)
                .setCallbackData(callBack);
        currentLine.add(button);
        return this;
    }

    public InlineKeyboardBuilder newLine() {
        currentLine = new ArrayList<>();
        keyboardLines.add(currentLine);
        return this;
    }

    public InlineKeyboardMarkup build() {
        return new InlineKeyboardMarkup().setKeyboard(keyboardLines);
    }

    protected String buildCallbackData(String buttonActionName) {
        return new StringBuilder()
                .append(buttonActionName)
                .append("_")
                .append(keyBoardName)
                //.append("_")
                //.append(new ButtonPosition(keyboardLines).toString())
                .toString();
    }

}