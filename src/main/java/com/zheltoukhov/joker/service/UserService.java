package com.zheltoukhov.joker.service;

import com.zheltoukhov.joker.entity.SpeechParams;
import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Maksim on 22.10.2017.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByChatId(Long chatId) {
        return userRepository.findUserByChatId(chatId);
    }

    public User addViewedPost(User user, String postId){
        user.addPost(postId);
        return userRepository.save(user);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User setSpeechParams(User user, SpeechParams speechParams) {
        user.setSpeechParams(speechParams);
        return userRepository.save(user);
    }
}
