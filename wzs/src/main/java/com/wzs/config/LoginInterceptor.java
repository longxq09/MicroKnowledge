package com.wzs.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Object user = request.getSession().getAttribute("userInfo");
        if (user == null) {

//            System.out.println(request.getServletPath());
            request.setAttribute("msg","没有权限登录");
//            response.sendRedirect("/index");
            return false;
        } else {
            return true;
        }

    }

}
