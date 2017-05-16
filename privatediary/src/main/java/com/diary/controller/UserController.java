package com.diary.controller;

import com.diary.entity.User;
import com.diary.service.UserService;
import com.diary.util.Sampler;
import com.diary.util.SendEmail;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.shcm.bean.SendResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gell on 2017/4/11.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/getUserByZh")
    void getUserByZh(HttpServletRequest request, HttpServletResponse response, String zh, String pwd) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String data = mapper.writeValueAsString(userService.getUserByZh(zh, pwd));

        if (data == null)
            data = "";

        out.append(data);
    }
    @RequestMapping("/getUserNicknameByZh")
    void getUserNicknameByZh(HttpServletRequest request, HttpServletResponse response, String zh) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String data = mapper.writeValueAsString(userService.getUserNicknameByZh(zh));

        if (data == null)
            data = "";

        out.append(data);
    }

    @RequestMapping("/uploadHeadImg")
    //上传头像
    void uploadHeadImg(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String zh, String pwd) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String data = mapper.writeValueAsString(userService.uploadHeadImg(file, request, zh, pwd));

        if (data == null)
            data = "";

        out.append(data);
    }

    @RequestMapping("/getAllUs")
    public void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String data = mapper.writeValueAsString(userService.getAllUsers());

        if (data == null)
            data = "";

        System.out.println(data);

        out.append(data);
    }

    //重置密码
    @RequestMapping("/resetPwd")
    public void  resetPwd(HttpServletRequest request, HttpServletResponse response, String username) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String data = mapper.writeValueAsString(userService.resetPwd(username));

        if (data == null)
            data = "";

        out.append(data);
    }

    //登录
    @RequestMapping("/signIn")
    public void signIn(String zh, String pwd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        Gson gson = new Gson();

        String data = mapper.writeValueAsString(gson.toJson(userService.signIn(zh, pwd)));

        System.out.println(zh);
        System.out.println(pwd);



            if (data != null)
                out.append(data);

        System.out.println("data:"+data);

        data = "";
    }

    //注册
    @RequestMapping("/signUp")
    public void signUp(HttpServletRequest request, HttpServletResponse response, User u, String num) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

        String json = mapper.writeValueAsString(userService.signUp(u, num));

        out.append(json);

        System.out.println(u);
        System.out.println(num);
        System.out.println("json:"+json);

        //return json;
    }

    //短信验证码
    @RequestMapping("/phoneCode")
    public void phoneCode(HttpServletRequest request, HttpServletResponse response, String phoneNum) throws Exception {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out=response.getWriter();

       /* String patternOfPhoneNum = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        */

        String   data = mapper.writeValueAsString(userService.sampler(phoneNum));


        out.append(data);
    }


    @RequestMapping("/emailCode")
    public void emailCode(HttpServletRequest request, HttpServletResponse response, String email) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        userService.sendEmail(email);
    }
}
