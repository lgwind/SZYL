package com.szyl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.szyl.entity.User;
import com.szyl.service.UserService;

public class UserServiceTest {
    
    private UserService userSevice = new UserService();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testuserSevice() {
        fail("Not yet implemented");
    }

    @Test
    public void testAdd() {
        User user = new User("志文","111","超级管理员");
        int result = userSevice.add(user);
        System.out.println(result);
    }

    @Test
    public void testDelete() {
        int result = userSevice.delete("志文");
        System.out.println(result);
    }

    @Test
    public void testUpdate() {
        User user = new User("志文","2222","普通用户");
        int result = userSevice.update(user);
        System.out.println(result);
    }

    @Test
    public void testGet() {
        User user = userSevice.get("志文");
        System.out.println(user);
    }

    @Test
    public void testGetAll() {
        List<User> user = userSevice.getAll();
        System.out.println(user);
    }

}
