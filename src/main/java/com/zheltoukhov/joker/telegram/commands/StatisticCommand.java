package com.zheltoukhov.joker.telegram.commands;

import com.zheltoukhov.joker.entity.StatRecord;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.helpers.StatRecordType;
import com.zheltoukhov.joker.service.StatisticService;
import com.zheltoukhov.joker.telegram.CommonAbstractAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.Message;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.zheltoukhov.joker.telegram.commands.Commands.STATISTIC_COMMAND;

/**
 * Created by Maksim on 25.11.2017.
 */
@Component(STATISTIC_COMMAND)
public class StatisticCommand extends CommonAbstractAction implements JokerCommand {

    @Autowired
    StatisticService statisticService;

    @Override
    public void execute(User user, Message message, Consumer<PartialBotApiMethod> botCallback) {
        if (!user.getChatId().equals(183375382L)) {
            return;
        }

        String[] types = message.getText().split(" ");

        List<StatRecord> records = types.length < 2 || types[1].equals(StatRecordType.VOICE.toLowerCase()) ?
                statisticService.getAllVoiceRecords() :
                statisticService.getStartRecords();

        String userRecordsCount = records
                .stream()
                .collect(
                        Collectors.groupingBy(
                        StatRecord::getUserName,
                        HashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .limit(30)
                .map(x-> x.getKey()+": "+x.getValue())
                .collect(Collectors.joining(";\n"));

        botCallback.accept(simpleTextResponse(userRecordsCount,user.getChatId()));

    }
}
