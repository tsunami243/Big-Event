package org.example.bigevent.controller;

import org.example.bigevent.pojo.Result;
import org.example.bigevent.pojo.User;
import org.example.bigevent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public Result register(String username, String password, String confirmPassword) {
        User u = userService.findByUserName(username);
        if (u == null) {
            userService.register(username,password);
            return Result.success();
        }
        else {
            return Result.error("用户已存在");
        }
    }
}
