package com.zheltoukhov.joker.helpers;

import static com.zheltoukhov.joker.telegram.commands.Commands.*;

/**
 * Created by Maksim on 18.11.2017.
 */
public interface MessageConstants {
    String REPEAT_ANECDOTE_PREFIX = "Повтор ";
    String ANECDOTE_NUMBER_PREFIX = "#";
    String EXCEPTION_MESSAGE = "Ой... Че-то пошло не так. Попробуй попозже.";
    String NO_JOKES = "Что-то не могу больше найти для тебя анекдотов... Похоже ты уже все пересмотрел)";
    String NO_SUCH_JOKE_BY_ID = "Что-то не могу найти анекдот...";

    String INCORRECT_START = "Начни ка с команды "+PREFIX+START_COMMAND+" :)";

    String MAIN_MENU_SETTINGS_LABEL = "Настройки голоса:     ";
    String SPEAKER_SETTINGS_LABEL =   "Выбери стендапера:    ";
    String EMOTION_SETTINGS_LABEL =   "Выбери эмоцию:        ";
    String SPEED_SETTINGS_LABEL =     "Выбери скорость:      ";
    String LANG_SETTINGS_LABEL =      "Выбери язык:          ";
    String BACK_BUTTON_LABEL =        "\uD83D\uDD19 Назад    ";
    String CHANGED_SETTINGS_POSTFIX = "\nНастройки изменены! Используй команду %s чтобы прослушать анекдот в новом варике";

    String REPEAT_BUTTON_LABEL = "Повторить";
    String SHOW_TEXT_BUTTON_LABEL = "Показать текст";

    String MAIN_MENU_SETTING_BUTTON_PATTERN = "%s [%s]";
    String SPEAKER_LABEL = "Стендапер";
    String LANG_LABEL = "Язык";
    String EMOTION_LABEL = "Эмоции";
    String SPEED_LABEL = "Скорость";

    String HELP_TEXT =  "Все просто: жмешь команду "+PREFIX+JOKE_COMMAND+" и я пришлю тебе анекдот, любезно озвученный стендпером Захаром. Анекдоты присылаю в порядке убывания их охуенности.\n" +
            "Еще рядом с записью будут две кнопочки: '"+SHOW_TEXT_BUTTON_LABEL+"' и '"+REPEAT_BUTTON_LABEL+"'. " +
            "Первая пришлет текст анекдота, если вдруг речь рассказчика была не оч. " +
            "Вторая пришлет заново этот анекдот. Это для того, чтобы послушать его в новых красках после изменеия настроек.\n\n" +
            "Теперь про настройки. Чтобы в них попасть, надо жмакнуть команду "+PREFIX+SETTING_COMMAND+". " +
            "Там множество настроек голоса, чтобы было смешно дохуя.";
    String START_COMMAND_TEXT =
            "Здарова! Готов поделиться с тобой самыми сочными анекдотами категории Б. \n\n" +
            HELP_TEXT +
            "\n\nНу и есть команда "+PREFIX+HELP_COMMAND+ " на случай, если у тебя память как у \uD83D\uDC1F.";

    String TO_MANY_REQUESTS = "Воу воу... Палехче! Слишком быстро анекдоты щелкаешь. Больше одного за %s сек нельзя";
}
