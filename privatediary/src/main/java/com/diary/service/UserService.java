package com.diary.service;

import com.diary.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Gell on 2017/4/1.
 */

public interface UserService {

    //通过用户名（username）获取用户
    User getUserByZh(String zh, String pwd);

    String getUserNicknameByZh(String zh);

    //上传头像
    Object uploadHeadImg(MultipartFile file, HttpServletRequest request, String zh, String pwd);

    //获取所用用户
    List<User> getAllUsers();

    //重置密码
    String resetPwd(String zh);


    //登录
    User signIn(String zh, String pwd);

    //注册
    String signUp(User u, String num);

    String sampler(String phoneNum) throws Exception;

    void sendEmail(String email);

}
