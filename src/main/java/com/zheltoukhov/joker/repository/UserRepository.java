package com.zheltoukhov.joker.repository;

import com.zheltoukhov.joker.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Maksim on 22.10.2017.
 */
@Repository
public interface UserRepository  extends MongoRepository<User, String> {
    User findUserByChatId(Long chatId);
}
