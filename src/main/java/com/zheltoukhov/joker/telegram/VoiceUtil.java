package com.zheltoukhov.joker.telegram;

import com.zheltoukhov.joker.JokeException;
import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.entity.Post;
import com.zheltoukhov.joker.entity.SpeechParams;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.speech.VoiceException;
import com.zheltoukhov.joker.speech.VoiceSynthesis;
import com.zheltoukhov.joker.speech.params.VoiceParameters;
import com.zheltoukhov.joker.speech.params.yandex.*;
import com.zheltoukhov.joker.telegram.keyboards.AbstractKeyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendVoice;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Created by Maksim on 11.11.2017.
 */
@Component
public class VoiceUtil {

    @Autowired
    private VoiceSynthesis yandexSpeech;

    private final ExecutorService executorService = Executors.newFixedThreadPool(20);


    public SendVoice performVoice(User user, Post post, Consumer<PartialBotApiMethod> botCallback,
                                  String caption, AbstractKeyboard keyboard, Integer replyMessage) throws JokeException {
        executorService.submit(new VoiceTask(user, post, botCallback, caption, keyboard, replyMessage));

        return null;

    }

    class VoiceTask implements Runnable {

        private Integer replyMessage;
        private Post post;
        private User user;
        private Consumer<PartialBotApiMethod> botCallback;
        private String caption;
        private AbstractKeyboard keyboard;

        public VoiceTask(User user, Post post, Consumer<PartialBotApiMethod> botCallback, String caption, AbstractKeyboard keyboard, Integer replyMessage) {
            this.post = post;
            this.user = user;
            this.botCallback = botCallback;
            this.caption = caption;
            this.keyboard = keyboard;
            this.replyMessage = replyMessage;
        }

        @Override
        public void run() {
            SpeechParams userParams = user.getSpeechParams();
            VoiceParameters parameters = new YandexVoiceParameters(userParams)
                    .setVoiceText(new VoiceText(post.getText()));

            try {
                InputStream voice = yandexSpeech.getVoice(parameters);
                SendVoice sendVoice = new SendVoice()
                        .setNewVoice("anecdote", voice)
                        .setChatId(user.getChatId())
                        .setCaption(caption);
                if (keyboard != null) sendVoice.setReplyMarkup(keyboard.getMarkup());
                if (replyMessage != null) sendVoice.setReplyToMessageId(replyMessage);
                botCallback.accept( sendVoice );

            } catch (VoiceException e) {
                e.printStackTrace();
                botCallback.accept(new SendMessage()
                        .setText(MessageConstants.EXCEPTION_MESSAGE)
                        .setChatId(user.getChatId())
                );
            }
        }
    }
}
