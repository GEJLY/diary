package com.diary.entity;

import javax.persistence.*;

/**
 * Created by Gell on 2017/4/1.
 */
@Entity
public class Diary {
    @Id
    private String did;

    private String nickname;
    private String title;
    private String weather;
    private String diaryType;
    private String context;


    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDiaryType() {
        return diaryType;
    }

    public void setDiaryType(String diaryType) {
        this.diaryType = diaryType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void createDiary(String nickname, String title, String weather, String diaryType, String context){
        this.context = context;
        this.diaryType = diaryType;
        this.nickname = nickname;
        this.weather = weather;
        this.title = title;

    }
}