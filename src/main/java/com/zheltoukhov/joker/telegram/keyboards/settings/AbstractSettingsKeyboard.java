package com.zheltoukhov.joker.telegram.keyboards.settings;

import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.speech.params.yandex.VoiceParam;
import com.zheltoukhov.joker.telegram.keyboards.AbstractKeyboard;
import com.zheltoukhov.joker.telegram.keyboards.InlineKeyboardBuilder;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Map;

import static com.zheltoukhov.joker.telegram.actions.Actions.BACK_TO_SETTINGS_BUTTON_ACTION;

/**
 * Created by Maksim on 12.11.2017.
 */
public abstract class AbstractSettingsKeyboard extends AbstractKeyboard{
    private static final String CHECKED = "\uD83D\uDD18 ";
    private static final String UNCHECKED = "⚪ ";

    public InlineKeyboardMarkup getSettingsKeyboard(String userVoiceParamVal) {
        InlineKeyboardBuilder builder = new InlineKeyboardBuilder();
        for (Map.Entry<String, ? extends VoiceParam> paramEntry : getParams().entrySet()) {
            String label = buildLabel(paramEntry.getValue(), userVoiceParamVal);
            String callback = buildCallback(paramEntry.getValue().getName(), paramEntry.getKey());

            builder
                    .addButton(label, callback)
                    .newLine();
        }
        builder.addButton(MessageConstants.BACK_BUTTON_LABEL, BACK_TO_SETTINGS_BUTTON_ACTION);
        return builder.build();
    }

    @Override
    public InlineKeyboardMarkup getMarkup() {
        return null;
    }

    protected abstract Map<String, ? extends VoiceParam> getParams();

    protected <T extends VoiceParam> String buildLabel(T param, String val) {
        boolean isEqual = param.getValue().equals(val);
        return (isEqual ? CHECKED : UNCHECKED) + param.getSettingName();
    }
}
