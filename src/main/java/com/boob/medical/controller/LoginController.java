package com.boob.medical.controller;

import com.boob.medical.entity.User;
import com.boob.medical.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping("login_page")
    public String loginPage() {
        return "login";
    }

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("do_login")
    public String login(User user, Model model,
                        HttpServletRequest request, HttpServletResponse response) {
        User dbUser = userService.checkUserLogin(user);
        if (dbUser != null) {
            request.getSession().setAttribute("user", dbUser);
            Cookie cookie = new Cookie("account", dbUser.getAccount());
            //设置全局共享
            cookie.setPath("/");
            //设置3天过期
            cookie.setMaxAge(60 * 60 * 24 * 3);
            response.addCookie(cookie);
            return "redirect:/index";
        } else {
            model.addAttribute("msg", "用户名密码错误");
            return "login";
        }
    }

    /**
     * 注册页面
     *
     * @return
     */
    @GetMapping("register_page")
    public String registerPage() {
        return "register";
    }

    /**
     * 注册
     *
     * @return
     */
    @PostMapping("do_register")
    public String register(User user, Model model) {
        boolean notExists = userService.checkUserRegister(user);
        if (notExists) {
            userService.saveUser(user);
            return "login";
        } else {
            model.addAttribute("msg", "用户已存在");
            return "register";
        }
    }


    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("do_logon")
    public String logon(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("account", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "login";
    }

}
