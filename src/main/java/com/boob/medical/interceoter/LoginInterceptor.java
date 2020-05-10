package com.boob.medical.interceoter;

import com.boob.medical.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login/login_page");
            return false;
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("account".equals(cookie.getName()) && cookie.getValue().equals(user.getAccount())) {
                return true;
            }
        }
        response.sendRedirect("/login/login_page");
        return false;
    }
}
