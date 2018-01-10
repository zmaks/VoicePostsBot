package com.zheltoukhov.joker.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by Maksim on 21.10.2017.
 */
public class Post {
    @Id
    private String id;

    private int num;
    private int postId;
    private String text;
    private int likes;
    private int views;
    private Float mark;
    private Float mark2;
    private boolean isViewed = false;

    public Post(int num, int postId, String text, int likes, int views) {
        this.num = num;
        this.postId = postId;
        this.text = text;
        this.likes = likes;
        this.views = views;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }


    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", text='" + text + '\'' +
                ", likes=" + likes +
                '}';
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Float getMark2() {
        return mark2;
    }

    public void setMark2(Float mark2) {
        this.mark2 = mark2;
    }
}

