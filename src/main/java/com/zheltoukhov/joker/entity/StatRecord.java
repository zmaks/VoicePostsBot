package com.zheltoukhov.joker.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Maksim on 19.11.2017.
 */
public class StatRecord {
    @Id
    private String id;
    private Long chatId;
    private String userName;
    private String text;
    private String type;
    private Date date;

    public StatRecord(Long chatId, String userName, String text, String type) {
        this.chatId = chatId;
        this.userName = userName;
        this.text = text;
        this.type = type;
        this.date = new Date();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
