package com.zheltoukhov.joker.telegram;

/**
 * Created by Maksim on 19.11.2017.
 */
public class Utils {

    public static String getTelegramUserName(org.telegram.telegrambots.api.objects.User user) {
        StringBuilder stringBuilder = new StringBuilder();
        if (user.getFirstName()!= null) stringBuilder.append(user.getFirstName());
        if (user.getLastName()!= null) stringBuilder
                .append(" ")
                .append(user.getLastName());
        if (user.getUserName()!= null) stringBuilder
                .append(" (")
                .append(user.getUserName())
                .append(")");
        return stringBuilder.toString();
    }
}
