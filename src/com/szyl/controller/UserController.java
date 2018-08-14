package com.szyl.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szyl.entity.User;
import com.szyl.service.UserService;

/**
 * �û���ӿ���
 * @author chennaile
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    private UserService userService = new UserService();
    
    /**
     * ����û�
     * @param user
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public int add(User user) {
        return userService.add(user);
    }
    
    /**
     * �޸��û�
     * @param user
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public int update(User user) {
        return userService.add(user);
    }
    
    /**
     * ɾ���û�
     * @param username
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public int delete(@RequestParam(value="username",required=true) String username) {
        return userService.delete(username);
    }
    
    /**
     * ��ȡ�û��������û�����
     * @param username
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public User get(@RequestParam(value="username",required=true) String username) {
        return userService.get(username);
    }
    
    /**
     * ��ȡ�����û�
     * @return
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public List<User> getAll() {
        return userService.getAll();
    }
    
}
