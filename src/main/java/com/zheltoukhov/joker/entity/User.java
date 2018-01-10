package com.zheltoukhov.joker.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim on 22.10.2017.
 */
public class User {
    @Id
    private String id;
    private String name;
    private Long chatId;
    private List<String> viewed;
    private SpeechParams speechParams;
    private int warnings;
    private boolean banned;
    private String context;

    public User(String name, Long chatId) {
        this.name = name;
        this.chatId = chatId;
    }

    public User(long chatId) {
        this.chatId = chatId;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public List<String> getViewed() {
        return viewed;
    }

    public void setViewed(List<String> viewed) {
        this.viewed = viewed;
    }

    public void addPost(String postId) {
        if (viewed == null) viewed = new ArrayList<>();
        viewed.add(postId);
    }

    public SpeechParams getSpeechParams() {
        if (speechParams == null) return new SpeechParams();
        return speechParams;
    }

    public void setSpeechParams(SpeechParams speechParams) {
        this.speechParams = speechParams;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWarnings() {
        return warnings;
    }

    public void addWarning() {
        this.warnings++;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
