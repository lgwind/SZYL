package com.szyl.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szyl.entity.User;
import com.szyl.service.UserService;

/**
 * 用户表接口类
 * @author chennaile
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    private UserService userService = new UserService();
    
    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public int add(User user) {
        return userService.add(user);
    }
    
    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public int update(User user) {
        return userService.add(user);
    }
    
    /**
     * 删除用户
     * @param username
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public int delete(@RequestParam(value="username",required=true) String username) {
        return userService.delete(username);
    }
    
    /**
     * 获取用户（根据用户名）
     * @param username
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public User get(@RequestParam(value="username",required=true) String username) {
        return userService.get(username);
    }
    
    /**
     * 获取所有用户
     * @return
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public List<User> getAll() {
        return userService.getAll();
    }
    
}
