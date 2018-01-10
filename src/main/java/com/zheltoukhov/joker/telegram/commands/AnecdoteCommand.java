package com.zheltoukhov.joker.telegram.commands;

import com.zheltoukhov.joker.JokeException;
import com.zheltoukhov.joker.aop.annotations.Loggable;
import com.zheltoukhov.joker.entity.Post;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.telegram.CommonAbstractAction;
import com.zheltoukhov.joker.telegram.VoiceUtil;
import com.zheltoukhov.joker.telegram.filters.SpecificCommandFilter;
import com.zheltoukhov.joker.telegram.filters.VoiceRequestsAmountFilter;
import com.zheltoukhov.joker.telegram.keyboards.VoiceKeyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.ActionType;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendChatAction;
import org.telegram.telegrambots.api.objects.Message;
import java.util.function.Consumer;

import static com.zheltoukhov.joker.telegram.commands.Commands.JOKE_COMMAND;

/**
 * Created by Maksim on 11.11.2017.
 */
@Component(JOKE_COMMAND)
public class AnecdoteCommand extends CommonAbstractAction implements JokerCommand{

    @Autowired
    private VoiceUtil voiceUtil;

    @Autowired
    @Qualifier("voiceRequestsAmountFilter")
    private SpecificCommandFilter filter;

    @Override
    public void execute(User user, Message message, Consumer<PartialBotApiMethod> botCallback) {
        if (!filter.execute(user, botCallback)) return;
        botCallback.accept(new SendChatAction().setAction(ActionType.RECORDAUDIO).setChatId(user.getChatId()));

        try {
            Post post = postService.getNotViewedPost(user.getViewed());
            userService.addViewedPost(user, post.getId());

            voiceUtil.performVoice(
                    user, post,
                    botCallback,
                    "#"+post.getNum(),
                    new VoiceKeyboard(true, post.getId()), null);

        } catch (JokeException e) {
            e.printStackTrace();
            botCallback.accept(simpleTextResponse("Немогу рассказать анекдот...", user.getChatId()));
        }
    }


}
