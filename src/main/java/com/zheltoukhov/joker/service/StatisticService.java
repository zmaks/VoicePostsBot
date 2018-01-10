package com.zheltoukhov.joker.service;

import com.zheltoukhov.joker.entity.StatRecord;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.helpers.StatRecordType;
import com.zheltoukhov.joker.repository.StatRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Maksim on 19.11.2017.
 */
@Service
public class StatisticService {
    @Value("${stat.count-seconds:5}")
    private Integer counterTimeSec;

    @Autowired
    private StatRecordRepository statRepository;

    public boolean isToManyVoiceRequests(Long chatId) {
        List<StatRecord> records = statRepository.findTopByChatIdAndTypeLikeOrderByDateDesc(chatId, StatRecordType.VOICE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -1*counterTimeSec);
        final Date date = calendar.getTime();
        return records.stream()
                .anyMatch(r -> r.getDate().compareTo(date) > 0);
    }

    public StatRecord addRecord(User user, String text, String type) {
        return statRepository.save(new StatRecord(user.getChatId(), user.getName(), text, type));
    }

    public List<StatRecord> getAllVoiceRecords() {
        return statRepository.findAllByType(StatRecordType.VOICE);
    }

    public List<StatRecord> getStartRecords() {
        return statRepository.findAllByType(StatRecordType.START);
    }
}
