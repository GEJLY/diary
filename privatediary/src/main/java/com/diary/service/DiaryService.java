package com.diary.service;

import com.diary.entity.Diary;

import java.util.List;

/**
 * Created by Gell on 2017/4/1.
 */
public interface DiaryService {

    //创建日记
    String create(Diary diary);

    List<Diary> getAllDiary();

    //通过昵称获取日记列表
    List<Diary> getDiaryListByNickname(String nickname);

    boolean delDiary(Diary diary);

    Diary getDiaryByNicknameAndType(String nickname, String type);

    Diary getDiaryById(String id);
}
