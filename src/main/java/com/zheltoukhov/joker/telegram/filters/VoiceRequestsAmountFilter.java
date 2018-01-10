package com.zheltoukhov.joker.telegram.filters;

import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.function.Consumer;

/**
 * Created by Maksim on 19.11.2017.
 */
@Component
public class VoiceRequestsAmountFilter implements SpecificCommandFilter {
    @Autowired
    private StatisticService statisticService;

    @Value("${stat.count-seconds}")
    private String voiceAmountRestriction;

    @Override
    public boolean execute(User user, Consumer<PartialBotApiMethod> botCallback) {
        boolean ok = !statisticService.isToManyVoiceRequests(user.getChatId());
        if (!ok) botCallback.accept(
                new SendMessage()
                        .setText(String.format(MessageConstants.TO_MANY_REQUESTS, voiceAmountRestriction))
                        .setChatId(user.getChatId())
        );
        return ok;
    }
}
