package com.zheltoukhov.joker.telegram;

import com.zheltoukhov.joker.aop.annotations.Loggable;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.service.UserService;
import com.zheltoukhov.joker.telegram.actions.ActionsProvider;
import com.zheltoukhov.joker.telegram.commands.CommandsProvider;
import com.zheltoukhov.joker.telegram.filters.FilterProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updateshandlers.SentCallback;

import java.io.Serializable;

/**
 * Created by Maksim on 31.10.2017.
 */
@Component
public class JokerBot extends TelegramLongPollingBot implements BotActionListener{

    @Value("${telegram.bot-name}")
    private String botUserName;
    @Value("${telegram.api-key}")
    private String botToken;

    @Autowired
    CommandsProvider commandsProvider;
    @Autowired
    ActionsProvider actionsProvider;
    @Autowired
    FilterProvider filterProvider;
    @Autowired
    UserService userService;

    public JokerBot(DefaultBotOptions options) {
        super(options);
    }

    public JokerBot() {

        this(new DefaultBotOptions());
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);
        if (chatId == null) return;

        User user = userService.getUserByChatId(chatId);

        if (user != null) {
            if (!filterProvider.performFilers(user, this::executeCallback)) return;
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            handleTextMessage(update.getMessage(), user);
        }

        if (update.hasCallbackQuery()) {
            handleCallbackQuery(update.getCallbackQuery(), user);
        }

    }

    private Long getChatId(Update update) {
        if (update.hasMessage())
            return update.getMessage().getChatId();
        if (update.hasCallbackQuery())
            return update.getCallbackQuery().getMessage().getChatId();
        return null;
    }

    private void handleCallbackQuery(CallbackQuery callbackQuery, User user) {
        actionsProvider.getCommand(callbackQuery.getData()).execute(user, callbackQuery, this::executeCallback);
    }

    private void handleTextMessage(Message message, User user) {
        if (message.isCommand()) {
            commandsProvider.getCommand(message.getText()).ifPresent(c -> c.execute(user, message, this::executeCallback));
        }
    }

    private void executeCallback(PartialBotApiMethod method) {
        try {
            if (method instanceof BotApiMethod) {
                executeAsync((BotApiMethod) method, new SentCallback<Serializable>() {
                    @Override
                    public void onResult(BotApiMethod<Serializable> method, Serializable response) {

                    }

                    @Override
                    public void onError(BotApiMethod<Serializable> method, TelegramApiRequestException apiException) {

                    }

                    @Override
                    public void onException(BotApiMethod<Serializable> method, Exception exception) {

                    }
                });
                //execute((BotApiMethod)method);
                return;
            }
            if (method instanceof SendVoice) {
                sendVoice((SendVoice) method);
                return;
            }

        } catch (TelegramApiException e) {

            e.printStackTrace();
        }
    }



    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

}
