package com.diary.controller;

import com.diary.entity.Diary;
import com.diary.service.DiaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Gell on 2017/4/12.
 */
@Controller
@RequestMapping("/diary")
public class DiaryController {

    @Resource
    DiaryService diaryService;

    //创建日记

    @RequestMapping("/create")
    public void create(HttpServletRequest request, HttpServletResponse response, Diary diary) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        Gson gson = new Gson();

        String json = mapper.writeValueAsString(gson.toJson(diaryService.create(diary)));

        System.out.println(json);

        if (json == null)
            json = "";

        out.append(json);
    }

    @RequestMapping("/getAllDiary")
    void getAllDiary(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String data = mapper.writeValueAsString(diaryService.getAllDiary());

        if (data == null)
            data = "";

        out.append(data);
    }

    //通过昵称获取日记列表
    @RequestMapping("/getDiaryListByNickname")
    void getDiaryListByNickname(HttpServletRequest request, HttpServletResponse response, String nickname) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String data = mapper.writeValueAsString(diaryService.getDiaryListByNickname(nickname));

        if (data == null)
            data = "";

        out.append(data);
    }

    @RequestMapping("/delDiary")
    void delDiary(HttpServletRequest request, HttpServletResponse response, Diary diary) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String data = mapper.writeValueAsString(diaryService.delDiary(diary));

        if (data == null)
            data = "";

        out.append(data);
    }

    @RequestMapping("/getDiaryByNicknameAndType")
    void getDiaryByNicknameAndType(HttpServletRequest request, HttpServletResponse response, String nickname, String type) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String data = mapper.writeValueAsString(diaryService.getDiaryByNicknameAndType(nickname,type));

        if (data == null)
            data = "";

        out.append(data);
    }

    @RequestMapping("/getDiaryById")
    void getDiaryById(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String data = mapper.writeValueAsString(diaryService.getDiaryById(id));

        if (data == null)
            data = "";

        out.append(data);
    }
}
