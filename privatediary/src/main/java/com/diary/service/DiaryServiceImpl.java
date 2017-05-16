package com.diary.service;

import com.diary.mapper.DiaryMapper;
import com.diary.entity.Diary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Gell on 2017/4/10.
 */


@Service
@Transactional
public class DiaryServiceImpl implements DiaryService {


    @Resource
    DiaryMapper diaryMapper;

    @Override
    public Diary getDiaryById(String id){
        return diaryMapper.getDiaryById(id);
    }

    @Override
    public String create(Diary diary) {
        return diaryMapper.create(diary) ? "success" : "fail";
    }

    @Override
    public List<Diary> getAllDiary() {
        return diaryMapper.getAllDiary();
    }

    @Override
    public List<Diary> getDiaryListByNickname(String nickname) {
        return diaryMapper.getDiaryListByNickname(nickname);
    }

    @Override
    public boolean delDiary(Diary diary) {
        return diaryMapper.delDiary(diary);
    }

    @Override
    public Diary getDiaryByNicknameAndType(String nickname, String type) {
        return diaryMapper.getDiaryByNicknameAndType(nickname, type);
    }
}
