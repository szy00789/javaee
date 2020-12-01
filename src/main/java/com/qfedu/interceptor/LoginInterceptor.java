package com.qfedu.interceptor;

import com.qfedu.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();  //相当于找到/user/query.do
        System.out.println(requestURI);
        //URI是统一资源标识符
        //url是统一资源定位符
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null) {
            String value = request.getHeader("X-Requested-With");
            if (value != null && value.equals("XMLHttpRequest")) {
                response.getWriter().write("{\"code\":0, \"info\":\"未登录\"}");
            }else {
                //非ajax请求的时候条撞到login.html
                response.sendRedirect(request.getContextPath() + "/login.html");
            }
            return false;
        }
        return true;
    }
}
