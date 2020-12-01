package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
    @Autowired
    private UserService userService;

    @RequestMapping("/upload1.do")
    @ResponseBody
    public JsonResult uploadFile(@RequestParam MultipartFile upfile, HttpSession session) {
        User u =(User) session.getAttribute("loginUser");
        if (u == null) {
            return new JsonResult(0, "用户未登录");
        }
        //1.上传到目录
        String dir = "d:/upload";
        //2.获取上传文件的名字
        String fileName = upfile.getOriginalFilename();
        System.out.println(fileName);
        //3.判断目录是否存在
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        //4.保存文件的file对象
        File newFile = new File(dir, fileName);
        try {
            //5.保存文件到文件夹中  d:/upload
            upfile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //6.修改数据库
        userService.updateHeadImg(u.getUid(), "/upload/" + fileName);
        return new JsonResult(1, "上传成功");
    }
}
