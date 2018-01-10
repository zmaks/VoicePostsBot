package com.zheltoukhov.joker.telegram.actions;

import com.zheltoukhov.joker.JokeException;
import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.entity.Post;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.telegram.CommonAbstractAction;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.ActionType;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendChatAction;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;

import java.util.function.Consumer;

import static com.zheltoukhov.joker.telegram.actions.Actions.BUTTON_ACTION_SPLITTER;
import static com.zheltoukhov.joker.telegram.actions.Actions.SHOW_BUTTON_ACTION;

/**
 * Created by Maksim on 11.11.2017.
 */
@Component(SHOW_BUTTON_ACTION)
public class ShowButtonAction extends CommonAbstractAction implements JokerAction {
    @Override
    public void execute(User user, CallbackQuery callbackQuery, Consumer<PartialBotApiMethod> botCallback) {
        long chatId = user.getChatId();
        botCallback.accept(new SendChatAction().setAction(ActionType.TYPING).setChatId(chatId));


        try {
            String[] parts = callbackQuery.getData().split(BUTTON_ACTION_SPLITTER);

            assert(parts.length == 2);
            String anecId = parts[1];

            Post post = postService.getPostById(anecId);

            /*EditMessageReplyMarkup replyMarkup = new EditMessageReplyMarkup()
                    .setChatId(String.valueOf(chatId))
                    .setMessageId(callbackQuery.getMessage().getMessageId())
                    .setReplyMarkup(new VoiceKeyboard(true, anecId).getMarkup());*/
            SendMessage sendMessage = new SendMessage()
                    .setChatId(chatId)
                    .setText(post.getText())
                    .setReplyToMessageId(callbackQuery.getMessage().getMessageId());
            botCallback.accept(sendMessage);
            //botCallback.accept(replyMarkup);


        } catch (JokeException e) {
            e.printStackTrace();
            botCallback.accept(simpleTextResponse(MessageConstants.EXCEPTION_MESSAGE, chatId));
        }
    }
}
