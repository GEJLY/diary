package com.diary.mapper;

import com.diary.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    //通过用户名（username）获取用户
    User getUserByZh(String zh, String pwd);

    String getUserNicknameByZh(String zh);


    //上传头像
    Object uploadHeadImg(String headImg, String zh, String pwd);

    //获取所用用户
    List<User> getAllUsers();

    //重置密码
    Boolean resetPwd(String zh);


    //登录
    Boolean signIn(String zh, String pwd);

    //注册
    Boolean signUp(User u);


}
