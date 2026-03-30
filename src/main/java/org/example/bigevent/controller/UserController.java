package org.example.bigevent.controller;

import jakarta.validation.constraints.Pattern;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.pojo.User;
import org.example.bigevent.service.UserService;
import org.example.bigevent.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^//S{5,16}$") String username,
                           @Pattern(regexp = "^//S{5,16}$")String password) {
            User u = userService.findByUserName(username);
            if (u == null) {
                userService.register(username,password);
                return Result.success();
            }
            else {
                return Result.error("用户已存在");
            }
        }
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^//S{5,16}$") String username,
                        @Pattern(regexp = "^//S{5,16}$")String password) {
        User u = userService.findByUserName(username);
        if (u == null) {
            return Result.error("用户名错误");
        }
        if (Md5Util.getMD5String(password).equals(u.getPassword())) {
            return Result.success("jwt token");
        }
        return Result.error("用户密码错误");
    }
}

