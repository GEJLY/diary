package com.diary.service;

import com.diary.mapper.UserMapper;
import com.diary.entity.User;
import com.diary.util.Sampler;
import com.diary.util.SendEmail;
import com.shcm.bean.SendResultBean;
import com.shcm.send.OpenApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements  UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private Environment env;

    // TODO: 生成随机码
    private String i  = String.valueOf((int)(Math.random()*10000));

    @Resource
    private UserMapper userMapper;


    @Override
    public User getUserByZh(String zh, String pwd) {
        return userMapper.getUserByZh(zh, pwd);
    }

    @Override
    public String getUserNicknameByZh(String username) {
        return userMapper.getUserNicknameByZh(username);
    }




    @Override
    public Object uploadHeadImg(MultipartFile file, HttpServletRequest request, String zh, String pwd) {

        HashMap<String, Object> ret = new HashMap<String, Object>();
        if (file != null) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();

                    // 当前app根目录
                    String rootPath = request.getServletContext().getRealPath("/");

                    // 需要上传的相对地址（application.properties中获取）
                    String relativePath = env.getProperty("headImg");

                    // 文件夹是否存在，不存在就创建
                    File dir = new File(rootPath + File.separator + relativePath);
                    if (!dir.exists())
                        dir.mkdirs();
                    String fileExtension = getFileExtension(file);

                    // 生成UUID样式的文件名
                    String filename = java.util.UUID.randomUUID().toString() + "." + fileExtension;

                    // 文件全名
                    String fullFilename = dir.getAbsolutePath() + File.separator + filename;

                    // 用户头像被访问路径
                    String relativeFile = relativePath + File.separator + filename;

                    // 保存图片
                    File serverFile = new File(fullFilename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();
                    LOGGER.info("Server File Location = " + serverFile.getAbsolutePath());

                    String serverPath = new URL(request.getScheme(), request.getServerName(), request.getServerPort(),
                            request.getContextPath()).toString();
                    ret.put("url", serverPath + "/" + relativeFile);


                    userMapper.uploadHeadImg(serverPath, zh, pwd);

                    return relativeFile;

                } catch (Exception e) {
                    LOGGER.info("error: {}", e);
                    ret.put("url", "none");
                }
            }
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public String resetPwd(String username) {

        return userMapper.resetPwd(username) ? "success" : "fail";

    }

    @Override
    public User signIn(String zh, String pwd) {
        User user = new User();



       if (userMapper.signIn(zh, pwd))
           user = userMapper.getUserByZh(zh, pwd);

        System.out.println(user);

       return user;
    }

    @Override
    public String signUp(User u, String num) {

        User user = u;

        if (num.equals(i)){
            if (userMapper.signUp(user)){
                return "success";
            }
        }

        return "fail";
    }


    @Override
    public String sampler(String phoneNum) throws Exception {

        // TODO：在项目启动时执行一次
        Sampler.init();

        String tpl = "欢迎使用私人日记，您的验证码为："+i+"，请在30分钟内输入验证，逾期后您需要重新获取一个验证码——私人日记";


        List<SendResultBean> listItem = OpenApi.sendOnce(phoneNum, tpl, 0, 0, "");

        if(listItem != null)
        {
            for(SendResultBean t:listItem)
            {
                if(t.getResult() < 1)
                {
                    System.out.println("发送提交失败:" + t.getErrMsg());
                    return "发送提交失败:" + t.getErrMsg();
                }
                System.out.println("发送成功: 消息编号<" + t.getMsgId() + "> 总数<" + t.getTotal() + "> 余额<" + t.getRemain() + ">");
                return "发送成功: 消息编号<" + t.getMsgId() + "> 总数<" + t.getTotal() + "> 余额<" + t.getRemain() + ">";
            }
        }
        return  "";

    }

    /**
     * 返回文件后缀名，如果有的话
     */
    public static String getFileExtension(MultipartFile file) {
        if (file == null) {
            return null;
        }

        String name = file.getOriginalFilename();
        int extIndex = name.lastIndexOf(".");

        if (extIndex == -1) {
            return "";
        } else {
            return name.substring(extIndex + 1);
        }
    }

    @Override
    public void sendEmail(String email){
        StringBuffer sb = new StringBuffer("下面是您的私人日记验证码，请尽快填写！</br>"+i+"</br>[私人日记]");
        //发送邮件
        SendEmail.send(email, sb.toString());

        System.out.println("发送邮件");
        System.out.println(i);

    }



}
