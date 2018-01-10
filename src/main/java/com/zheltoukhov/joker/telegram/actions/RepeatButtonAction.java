package com.zheltoukhov.joker.telegram.actions;

import com.zheltoukhov.joker.JokeException;
import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.entity.Post;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.telegram.CommonAbstractAction;
import com.zheltoukhov.joker.telegram.VoiceUtil;
import com.zheltoukhov.joker.telegram.filters.SpecificCommandFilter;
import com.zheltoukhov.joker.telegram.filters.VoiceRequestsAmountFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.ActionType;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendChatAction;
import org.telegram.telegrambots.api.objects.CallbackQuery;

import java.util.function.Consumer;

import static com.zheltoukhov.joker.telegram.actions.Actions.BUTTON_ACTION_SPLITTER;
import static com.zheltoukhov.joker.telegram.actions.Actions.REPEAT_BUTTON_ACTION;

/**
 * Created by Maksim on 11.11.2017.
 */
@Component(REPEAT_BUTTON_ACTION)
public class RepeatButtonAction extends CommonAbstractAction implements JokerAction{

    @Autowired
    private VoiceUtil voiceUtil;

    @Autowired
    @Qualifier("voiceRequestsAmountFilter")
    private SpecificCommandFilter filter;

    @Override
    public void execute(User user, CallbackQuery callbackQuery, Consumer<PartialBotApiMethod> botCallback) {
        if (!filter.execute(user, botCallback)) return;
        botCallback.accept(new SendChatAction().setAction(ActionType.RECORDAUDIO).setChatId(user.getChatId()));

        try {
            String[] parts = callbackQuery.getData().split(BUTTON_ACTION_SPLITTER);

            assert(parts.length == 2);
            String anecId = parts[1];
            Post post = postService.getPostById(anecId);
            voiceUtil.performVoice(
                    user, post,
                    botCallback,
                    MessageConstants.REPEAT_ANECDOTE_PREFIX +MessageConstants.ANECDOTE_NUMBER_PREFIX +post.getNum(),
                    null,
                    callbackQuery.getMessage().getMessageId()
            );
            //botCallback.accept(sendVoice);

        } catch (JokeException e) {
            e.printStackTrace();
            botCallback.accept(simpleTextResponse("Чет не могу(", user.getChatId()));
        }
    }
}
