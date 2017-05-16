package com.diary.mapper;

import com.diary.entity.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Gell on 2017/4/10.
 */
@Mapper
@Repository
public interface DiaryMapper {
    //创建日记
    Boolean create(Diary diary);

    List<Diary> getAllDiary();

    //通过昵称获取日记列表
    List<Diary> getDiaryListByNickname(String nickname);

    Diary getDiaryByNicknameAndType(String nickname, String type);

    boolean delDiary(Diary diary);

    Diary getDiaryById(String id);
}
