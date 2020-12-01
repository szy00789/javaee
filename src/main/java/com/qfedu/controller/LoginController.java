package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Controller
@Validated //如果想要在方法的参数上使用验证注解，就必须要在这个类上使用这个注解
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
//    public JsonResult login(@NotEmpty(message = "{name.empty}") String bankCode, @Size(min = 1, max = 8, message = "密码长度不匹配") String password, HttpSession session) {
//        User user = userService.findByCode(bankCode, password);
//        session.setAttribute("loginUser", user);
//        return new JsonResult(1, "登录成功");
//    }

//    public JsonResult login(@NotEmpty(message = "{name.empty}")String bankCode,
//                            @Size(min = 1, max = 8,message = "密码长度不匹配") String password, HttpSession session) {
//        User user = userService.findByCode(bankCode,password);
//        session.setAttribute("loginUser",user);
//        return new JsonResult(1,"登录成功");
//    }
//

    public JsonResult login(@NotEmpty(message = "{name.empty}")String bankCode,
                            @Size(min = 1, max = 8,message = "密码长度不匹配") String password, HttpSession session) {
        User user = userService.findByCode(bankCode, password);
        session.setAttribute("loginUser",user);
        return new JsonResult(1,"登录成功");
    }
    //
    //
}
