package com.zheltoukhov.joker.telegram;

import com.zheltoukhov.joker.service.PostService;
import com.zheltoukhov.joker.service.UserService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;

/**
 * Created by Maksim on 04.11.2017.
 */
@Configurable(preConstruction=true,dependencyCheck=true,autowire=Autowire.BY_TYPE)
public abstract class CommonAbstractAction {

    @Autowired
    protected PostService postService;
    @Autowired
    protected UserService userService;

    protected PartialBotApiMethod simpleTextResponse(String text, Long chatId) {
        return new SendMessage()
                .setChatId(chatId)
                .setText(text)
                .setReplyMarkup(new ReplyKeyboardRemove());
    }
}
