package com.zheltoukhov.joker.repository;

import com.zheltoukhov.joker.entity.StatRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Maksim on 19.11.2017.
 */
@Repository
public interface StatRecordRepository extends MongoRepository<StatRecord, String> {
    List<StatRecord> findTopByChatIdAndTypeLikeOrderByDateDesc(Long chatId, String type);
    List<StatRecord> findAllByType(String type);
}
