package com.boob.medical.controller;

import com.boob.medical.entity.User;
import com.boob.medical.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("find_by_id")
    public String findById() {
        return "profile";
    }

    @PostMapping("update_profile")
    public String update(User user, HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        user.setGmtCreated(sessionUser.getGmtCreated())
                .setAccount(sessionUser.getAccount())
                .setPower(sessionUser.getPower())
                .setId(sessionUser.getId());
        userService.updateUser(user);
        request.getSession().setAttribute("user", userService.getUserById(user.getId()));
        return "profile";
    }

}
